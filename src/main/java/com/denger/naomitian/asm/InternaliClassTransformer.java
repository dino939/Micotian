package com.denger.naomitian.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import com.denger.naomitian.asm.visitors.*;

import java.util.Arrays;

public class InternaliClassTransformer implements IClassTransformer {

    private static final String[] transformedClasses = {
            "net.minecraft.client.renderer.chunk.VisGraph",
            "net.minecraft.client.renderer.EntityRenderer",
            "net.minecraft.network.NetworkManager",
            "net.minecraft.client.renderer.BlockRendererDispatcher",
            "net.minecraft.block.state.BlockStateContainer$StateImplementation",
            "net.minecraft.block.BlockLiquid",
            "net.minecraft.client.entity.EntityPlayerSP"
    };

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        boolean isObfuscated = !name.equals(transformedName);
        int index = Arrays.asList(transformedClasses).indexOf(transformedName);
        return index != -1 ? transform(index, basicClass, isObfuscated) : basicClass;
    }

    public byte[] transform(int index, byte[] basicClass, boolean isObfuscated) {
        System.out.println("Transforming " + transformedClasses[index]);
        try {
            ClassVisitor classVisitor;
            ClassReader classReader = new ClassReader(basicClass);
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            switch (index) {
                case 0:
                    classVisitor = new VisGraphVisitor(classWriter, isObfuscated);
                    break;
                case 1:
                    classVisitor = new EntityRendererVisitor(classWriter, isObfuscated);
                    break;
                case 2:
                    classVisitor = new NetworkManagerVisitor(classWriter, isObfuscated);
                    break;
                case 3:
                    classVisitor = new BlockRendererDispatcherVisitor(classWriter, isObfuscated);
                    break;
                case 4:
                    classVisitor = new BlockStateContainerVisitor(classWriter, isObfuscated);
                    break;
                case 5:
                    classVisitor = new BlockLiquidVisitor(classWriter, isObfuscated);
                    break;
                case 6:
                    classVisitor = new EntityPlayerSPVisitor(classWriter, isObfuscated);
                    break;
                default:
                    classVisitor = new ClassVisitor(Opcodes.ASM5, classWriter) {};
            }
            classReader.accept(classVisitor, 0);
            return classWriter.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basicClass;
    }
}