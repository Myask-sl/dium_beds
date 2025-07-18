package placeholder.pacname.dium_beds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static placeholder.pacname.dium_beds.diumBeds.DIUM_BE_T;

public class diumBedBlockEntity extends BlockEntity {
    private DyeColor color;
    public diumBedBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(DIUM_BE_T.get(), blockPos, blockState);
        this.color = ((BedBlock)blockState.getBlock()).getColor();
    }

 /*   public diumBedBlockEntity(BlockEntityType<?> BET, BlockPos blockPos, BlockState blockState) {
        super(BET, blockPos, blockState);
    }//allow passthrough unlike stupid Bed.
*/
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider holUpPro) {
        super.saveAdditional(tag, holUpPro);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider holUpPro) {
        super.loadAdditional(tag, holUpPro);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public DyeColor getColor() {
        return this.color;
    }

    public void setColor(DyeColor p_58730_) {
        this.color = p_58730_;
    }
}
