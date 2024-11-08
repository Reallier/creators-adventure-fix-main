package dev.misakacloud.ctsadvfix.ultimine.shape;

import dev.ftb.mods.ftbultimine.EntityDistanceComparator;
import dev.ftb.mods.ftbultimine.shape.BlockMatcher;
import dev.ftb.mods.ftbultimine.shape.Shape;
import dev.ftb.mods.ftbultimine.shape.ShapeContext;
import net.minecraft.core.BlockPos;

import java.util.*;


public class BigFlatShape implements Shape {
    public static final BlockPos[] NEIGHBOR_POSITIONS_SAME_Y = new BlockPos[4];

    static {
        NEIGHBOR_POSITIONS_SAME_Y[0] = new BlockPos(1, 0, 0);
        NEIGHBOR_POSITIONS_SAME_Y[1] = new BlockPos(-1, 0, 0);
        NEIGHBOR_POSITIONS_SAME_Y[2] = new BlockPos(0, 0, 1);
        NEIGHBOR_POSITIONS_SAME_Y[3] = new BlockPos(0, 0, -1);
    }

    @Override
    public String getName() {
        return "big_flat";
    }

    @Override
    public BlockMatcher getTagMatcher() {
        return (original, state) -> !state.isAir();
    }

    @Override
    public List<BlockPos> getBlocks(ShapeContext context) {
        HashSet<BlockPos> known = new HashSet<>();
        bfsWalk(context, known);

        List<BlockPos> list = new ArrayList<>(known);
        list.sort(new EntityDistanceComparator(context.pos()));

        if (list.size() > context.maxBlocks()) {
            list.subList(context.maxBlocks(), list.size()).clear();
        }
        return list;
    }

    /**
     * 广度优先搜索,但是只搜索平面
     *
     * @param context
     * @param known
     */
    private void bfsWalk(ShapeContext context, HashSet<BlockPos> known) {
        Set<BlockPos> traversed = new HashSet<>();
        Deque<BlockPos> openSet = new ArrayDeque<>();
        openSet.add(context.pos());
        traversed.add(context.pos());

        while (!openSet.isEmpty()) {
            BlockPos ptr = openSet.pop();

            // 这里去掉一个 check 我们不需要考虑是不是一样的方块
            if (context.check(ptr) && known.add(ptr)) {
                if (known.size() >= context.maxBlocks()) {
                    return;
                }

                for (BlockPos side : NEIGHBOR_POSITIONS_SAME_Y) {
                    BlockPos offset = ptr.offset(side);

                    if (traversed.add(offset)) {
                        openSet.add(offset);
                    }
                }
            }
        }
    }
}
