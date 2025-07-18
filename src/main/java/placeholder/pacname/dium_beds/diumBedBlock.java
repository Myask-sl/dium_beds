package placeholder.pacname.dium_beds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class diumBedBlock extends BedBlock {
    private static final int THICKNESS = 6;
    private static final int LEG_WIDTH = 2;
    private static final int LEG_HEIGHT = 5;
    private static final int HEADBOARD_HEIGHT = 5;
    private static final int HEADBOARD_THICK = LEG_WIDTH;
    protected static final VoxelShape BASE = Block.box(0.0, LEG_HEIGHT, 0.0, 16.0, LEG_HEIGHT+THICKNESS, 16.0);
    protected static final VoxelShape HEADBOARD_NORTH = Block.box(0.0,LEG_HEIGHT+THICKNESS,0.0,16.0,LEG_HEIGHT+THICKNESS+HEADBOARD_HEIGHT,HEADBOARD_THICK);
    protected static final VoxelShape HEADBOARD_SOUTH = Block.box(0.0,LEG_HEIGHT+THICKNESS,16.0-HEADBOARD_THICK,16.0,LEG_HEIGHT+THICKNESS+HEADBOARD_HEIGHT,16.0);
    protected static final VoxelShape HEADBOARD_WEST = Block.box(0.0,LEG_HEIGHT+THICKNESS,0.0,HEADBOARD_THICK,LEG_HEIGHT+THICKNESS+HEADBOARD_HEIGHT,16.0);
    protected static final VoxelShape HEADBOARD_EAST = Block.box(16.0-HEADBOARD_THICK,LEG_HEIGHT+THICKNESS,0.0,16.0,LEG_HEIGHT+THICKNESS+HEADBOARD_HEIGHT,16.0);
    protected static final VoxelShape LEG_NORTH_WEST = Block.box(0.0, 0.0, 0.0, LEG_WIDTH, LEG_HEIGHT, LEG_WIDTH);
    protected static final VoxelShape LEG_SOUTH_WEST = Block.box(0.0, 0.0, 16.0-LEG_WIDTH, LEG_WIDTH, LEG_HEIGHT, 16.0);
    protected static final VoxelShape LEG_NORTH_EAST = Block.box(16.0-LEG_WIDTH, 0.0, 0.0, 16.0, LEG_HEIGHT, LEG_WIDTH);
    protected static final VoxelShape LEG_SOUTH_EAST = Block.box(16.0-LEG_WIDTH, 0.0, 16.0-LEG_WIDTH, 16.0, LEG_HEIGHT, 16.0);
    protected static final VoxelShape NORTH_FOOT_SHAPE = Shapes.or(BASE, LEG_NORTH_WEST, LEG_NORTH_EAST);
    protected static final VoxelShape SOUTH_FOOT_SHAPE = Shapes.or(BASE, LEG_SOUTH_WEST, LEG_SOUTH_EAST);
    protected static final VoxelShape WEST_FOOT_SHAPE = Shapes.or(BASE, LEG_NORTH_WEST, LEG_SOUTH_WEST);
    protected static final VoxelShape EAST_FOOT_SHAPE = Shapes.or(BASE, LEG_NORTH_EAST, LEG_SOUTH_EAST);
    protected static final VoxelShape NORTH_HEAD_SHAPE = Shapes.or(NORTH_FOOT_SHAPE,HEADBOARD_NORTH);
    protected static final VoxelShape SOUTH_HEAD_SHAPE = Shapes.or(SOUTH_FOOT_SHAPE,HEADBOARD_SOUTH);
    protected static final VoxelShape WEST_HEAD_SHAPE = Shapes.or(WEST_FOOT_SHAPE,HEADBOARD_WEST);
    protected static final VoxelShape EAST_HEAD_SHAPE = Shapes.or(EAST_FOOT_SHAPE,HEADBOARD_EAST);

    private final DyeColor color;
    private final boolean aqua;

    public diumBedBlock(DyeColor dc, Properties p) {
        super(dc, p);
        color = dc;
        aqua = false;
    }
    public diumBedBlock(DyeColor dc, Properties p, boolean b) {
        super(dc, p);
        color = dc;
        aqua = b;
    }

    @Override
    public boolean hidesNeighborFace(BlockGetter level, BlockPos pos, BlockState state, BlockState neighborState, Direction dir) {
        return super.hidesNeighborFace(level, pos, state, neighborState, dir);
    }

    @Override
    protected VoxelShape getShape(BlockState BS, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        Direction direction = BS.getValue(FACING);
        boolean isHead = (BS.getValue(PART) == BedPart.HEAD);
        switch (direction) {
            case NORTH:
                return isHead ? NORTH_HEAD_SHAPE : SOUTH_FOOT_SHAPE;
            case SOUTH:
                return isHead ? SOUTH_HEAD_SHAPE : NORTH_FOOT_SHAPE;
            case WEST:
                return isHead ? WEST_HEAD_SHAPE : EAST_FOOT_SHAPE;
            default:
                return isHead ? EAST_HEAD_SHAPE : WEST_FOOT_SHAPE;
        }
    }
    public boolean isAqua() {return aqua;}

    @Override
    public BlockEntity newBlockEntity(BlockPos bPos, BlockState BS) {
        return new diumBedBlockEntity(bPos,BS);
    }
}
