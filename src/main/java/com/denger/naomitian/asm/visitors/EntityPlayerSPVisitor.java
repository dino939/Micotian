package com.denger.naomitian.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/*
Posts an PlayerIsUserEvent that allows you to see yourself in third person
 */
public class EntityPlayerSPVisitor extends ClassVisitor {
    final String IS_USER;
    final String IS_USER_DESC;

    final String MOVE;
    final String MOVE_DESC;

    final String ON_UPDATE_WALKING_PLAYER;
    final String ON_UPDATE_WALKING_PLAYER_DESC;

    public EntityPlayerSPVisitor(ClassVisitor cv, boolean isObfuscated) {
        super(ASM5, cv);
        this.IS_USER = isObfuscated ? "cZ" : "isUser";
        this.IS_USER_DESC = "()Z";
        this.MOVE = isObfuscated ? "a" : "move";
        this.MOVE_DESC = isObfuscated ? "(Lvv;DDD)V" : "(Lnet/minecraft/entity/MoverType;DDD)V";
        this.ON_UPDATE_WALKING_PLAYER = isObfuscated ? "N" : "onUpdateWalkingPlayer";
        this.ON_UPDATE_WALKING_PLAYER_DESC = "()V";
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals(IS_USER) && desc.equals(IS_USER_DESC)) {
            System.out.println("Method " + name + " transformed");
            mv = new IsUserVisitor(mv);
        } else if (name.equals(MOVE) && desc.equals(MOVE_DESC)) {
            System.out.println("Method " + name + " transformed");
            mv = new MoveVisitor(mv);
        } else if (name.equals(ON_UPDATE_WALKING_PLAYER) && desc.equals(ON_UPDATE_WALKING_PLAYER_DESC)) {
            System.out.println("Method " + name + " transformed");
            mv = new OnUpdateWalkingPlayer(mv);
        }
        return mv;
    }

    public static class IsUserVisitor extends MethodVisitor {
        public IsUserVisitor(MethodVisitor mv) {
            super(ASM5, mv);
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode == IRETURN) {
                mv.visitMethodInsn(INVOKESTATIC, "ru/internali/utils/EventFactory", "isUser", "()Z", false);
            }
            super.visitInsn(opcode);
        }
    }

    public static class MoveVisitor extends MethodVisitor {
        public MoveVisitor(MethodVisitor mv) {
            super(ASM5, mv);
        }

        @Override
        public void visitCode() {
            mv.visitTypeInsn(NEW, "ru/terrar/bobr/events/PlayerMoveEvent");
            mv.visitInsn(DUP);
            mv.visitVarInsn(DLOAD, 2);
            mv.visitVarInsn(DLOAD, 4);
            mv.visitVarInsn(DLOAD, 6);
            mv.visitMethodInsn(INVOKESPECIAL, "ru/internali/events/PlayerMoveEvent", "<init>", "(DDD)V", false);
            mv.visitVarInsn(ASTORE, 8);
            mv.visitFieldInsn(GETSTATIC, "net/minecraftforge/common/MinecraftForge", "EVENT_BUS", "Lnet/minecraftforge/fml/common/eventhandler/EventBus;");
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraftforge/fml/common/eventhandler/EventBus", "post", "(Lnet/minecraftforge/fml/common/eventhandler/Event;)Z", false);
            Label l0 = new Label();
            mv.visitJumpInsn(IFEQ, l0);
            mv.visitInsn(RETURN);
            mv.visitLabel(l0);
            mv.visitFrame(F_APPEND, 1, new Object[]{"ru/internali/events/PlayerMoveEvent"}, 0, null);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitFieldInsn(GETFIELD, "ru/internali/events/PlayerMoveEvent", "x", "D");
            mv.visitVarInsn(DSTORE, 2);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitFieldInsn(GETFIELD, "ru/internali/events/PlayerMoveEvent", "y", "D");
            mv.visitVarInsn(DSTORE, 4);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitFieldInsn(GETFIELD, "ru/internali/events/PlayerMoveEvent", "z", "D");
            mv.visitVarInsn(DSTORE, 6);
            super.visitCode();
        }
    }

    public static class OnUpdateWalkingPlayer extends MethodVisitor {
        public OnUpdateWalkingPlayer(MethodVisitor mv) {
            super(ASM5, mv);
        }

        @Override
        public void visitCode() {
            super.visitCode();
            mv.visitMethodInsn(INVOKESTATIC, "ru/internali/utils/EventFactory", "onUpdateWalkingPlayer", "()Z", false);
            Label l1 = new Label();
            mv.visitJumpInsn(IFEQ, l1);
            mv.visitInsn(RETURN);
            mv.visitLabel(l1);
        }
    }
}
