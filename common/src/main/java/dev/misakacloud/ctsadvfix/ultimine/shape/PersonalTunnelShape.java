package dev.misakacloud.ctsadvfix.ultimine.shape;

import dev.ftb.mods.ftbultimine.shape.BlockMatcher;
import dev.ftb.mods.ftbultimine.shape.Shape;
import dev.ftb.mods.ftbultimine.shape.ShapeContext;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class PersonalTunnelShape implements Shape {
    @Override
    public String getName() {
        return "personal_tunnel";
    }

    @Override
    public BlockMatcher getTagMatcher() {
        return (original, state) -> !state.isAir();
    }

    @Override
    public List<BlockPos> getBlocks(ShapeContext context) {
        List<BlockPos> list = new ArrayList<>(context.maxBlocks());
//        System.out.println("block Pos: " + context.pos.getX() + " " + context.pos.getY() + " " + context.pos.getZ());
        // 获取一个玩家的位置的 Y
        double playerY = context.player().getY();
        int blockY = context.pos().getY();
        for (int i = 0; i < context.maxBlocks() / 2; i++) {
            // 这是标准的一行
            // 不修改 Y
            BlockPos p = new BlockPos(
                    context.pos().getX() - context.face().getStepX() * i,
                    context.pos().getY(),
                    context.pos().getZ() - context.face().getStepZ() * i);
            if (context.block(p).isAir()) {
                // 标记空气坐标
//                System.out.println("air Pos: " + p.getX() + " " + p.getY() + " " + p.getZ());
                break;
            }
            list.add(p);

            // 再附加一行能拼成一人高度
            // 具体原理是,如果玩家坐标比方块坐标低,那么就往上挖,否则就往下挖
//            System.out.println("Y 差值: "+playerY + " " + blockY + " " + (playerY - blockY));
            BlockPos anotherPos = new BlockPos(
                    context.pos().getX() - context.face().getStepX() * i,
                    context.pos().getY() + ((playerY - blockY) < 0 ? -1 : 1),
                    context.pos().getZ() - context.face().getStepZ() * i);
            if (context.block(p).isAir()) {
                // 标记空气坐标
//                System.out.println("air Pos: " + p.getX() + " " + p.getY() + " " + p.getZ());
                break;
            }
            list.add(anotherPos);

        }

        return list;
    }
}
