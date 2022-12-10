package com.denger.naomitian.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.network.Packet;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;
import com.denger.naomitian.events.*;


public class EventFactory {
    public static boolean setOpaqueCube() {
        SetOpaqueCubeEvent setOpaqueCube = new SetOpaqueCubeEvent();
        return MinecraftForge.EVENT_BUS.post(setOpaqueCube);
    }

    public static Packet<?> onSendPacket(Packet<?> packet) {
        SendPacketEvent event = new SendPacketEvent(packet);
        return MinecraftForge.EVENT_BUS.post(event) ? null : event.getPacket();
    }

    public static Packet<?> onReceivePacket(Packet<?> packet) {
        ReceivePacketEvent event = new ReceivePacketEvent(packet);
        return MinecraftForge.EVENT_BUS.post(event) ? null : event.getPacket();
    }

    public static boolean renderBlock(IBlockState stateIn) {
        return MinecraftForge.EVENT_BUS.post(new RenderModelEvent(stateIn));
    }

    public static boolean shouldSideBeRendered(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing facing) {
        ShouldSideBeRenderedEvent event = new ShouldSideBeRenderedEvent(state, state.getBlock().shouldSideBeRendered(state, blockAccess, pos, facing));
        MinecraftForge.EVENT_BUS.post(event);
        return event.getShouldBeRendered();
    }

    public static float getAmbientOcclusionLightValue(IBlockState state) {
        GetAmbientOcclusionLightValueEvent event = new GetAmbientOcclusionLightValueEvent(state);
        MinecraftForge.EVENT_BUS.post(event);
        return event.getLightValue();
    }

    public static AxisAlignedBB getCollisionBoundingBox() {
        GetLiquidCollisionBoundingBoxEvent event = new GetLiquidCollisionBoundingBoxEvent();
        MinecraftForge.EVENT_BUS.post(event);
        return event.getCollisionBoundingBox();
    }

    public static boolean isUser() {
        return !MinecraftForge.EVENT_BUS.post(new PlayerIsUserEvent());
    }

    public static boolean onUpdateWalkingPlayer() {
        return MinecraftForge.EVENT_BUS.post(new UpdateWalkingPlayerEvent());
    }
}
