package invalid.myask.dium_beds;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;

import java.util.Arrays;
import java.util.Comparator;

public class diumBedRenderer implements BlockEntityRenderer<diumBedBlockEntity> {
    private final BlockEntityRendererProvider.Context BERPcon;
    private final ModelPart headR = formTheHead().bakeRoot();
    private final ModelPart footR = formTheFoot().bakeRoot();
    public static final ResourceLocation DIUMBED_SHEET = InventoryMenu.BLOCK_ATLAS;
                            //.fromNamespaceAndPath(diumBeds.MODID,"textures/atlas/diumbeds.png");//todo: fix to not overwrite
    public static final Material[] DIUM_BED_TEXTURES = Arrays.stream(DyeColor.values())
            .sorted(Comparator.comparingInt(DyeColor::getId))
            .map(color -> new Material(DIUMBED_SHEET, ResourceLocation.fromNamespaceAndPath(diumBeds.MODID,"block/"+color.getName()+"_dium_bed")))
            .toArray(Material[]::new);
    public static final Material BEDDIUM_BED_TEXTURE = new Material (InventoryMenu.BLOCK_ATLAS,
            ResourceLocation.fromNamespaceAndPath(diumBeds.MODID,"block/beddium_bed"));
    public static final Material AQUA_DIUM_BED_TEXTURE = new Material (InventoryMenu.BLOCK_ATLAS,
            ResourceLocation.fromNamespaceAndPath(diumBeds.MODID,"block/aqua_dium_bed"));

    public diumBedRenderer(BlockEntityRendererProvider.Context contextor) {
        //super(BERPcon);
        BERPcon = contextor;

    }

    public static LayerDefinition formTheHead(){
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition head = partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(50, 24).addBox(13.0F, -1.0F, 10.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(50, 24).addBox(-1.0F, -1.0F, 10.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 7).addBox(-1.0F, 6.0F, 4.0F, 16.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(22, 1).addBox(0.0F, 1.0F, 5.0F, 14.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(-1.0F, 1.0F, 6.0F, 16.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, -6.0F));//PartPose.offset(-7.0F, 24.0F, -7.0F));

        PartDefinition headboard = head.addOrReplaceChild("headboard", CubeListBuilder.create().texOffs(18, 55).addBox(-15.0F, 2.0F, 3.0F, 16.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(23, 51).addBox(-15.0F, 2.0F, -1.0F, 16.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(52, 31).addBox(-15.0F, 2.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(52, 31).addBox(-3.0F, 2.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(14.0F, -3.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }
    public static LayerDefinition formTheFoot(){
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition foot = partDefinition.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(50, 24).addBox(13.0F, 29.0F, -20.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(50, 24).addBox(-1.0F, 29.0F, -20.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)) //(float)Math.PI
                .texOffs(0, 22).addBox(-1.0F, 15.0F, -26.0F, 16.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F,-15.0F,24.0F,(float)Math.PI,(float)Math.PI,(float)Math.PI));//PartPose.offset(-7.0F, 24.0F, 23.0F));
        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    public void render(diumBedBlockEntity dBBE, float timeElapsed, PoseStack poseStack, MultiBufferSource MBS, int combinedLight, int combinedOverlay) {
        Material material;
        if (((diumBedBlock)dBBE.getBlockState().getBlock()).isAqua())
            material = AQUA_DIUM_BED_TEXTURE;
        else material = (dBBE.getColor() != null) ? DIUM_BED_TEXTURES[dBBE.getColor().getId()] : BEDDIUM_BED_TEXTURE;
        Level level = dBBE.getLevel();
        if (level != null) {
            BlockState blockstate = dBBE.getBlockState();
            DoubleBlockCombiner.NeighborCombineResult<? extends diumBedBlockEntity> neighborcombineresult = DoubleBlockCombiner.combineWithNeigbour(
                    diumBeds.DIUM_BE_T.get(),
                    diumBedBlock::getBlockType,
                    diumBedBlock::getConnectedDirection,
                    ChestBlock.FACING,
                    blockstate,
                    level,
                    dBBE.getBlockPos(),
                    (levAccessor, bPos) -> false
            );
            int i = neighborcombineresult.apply(new BrightnessCombiner<>()).get(combinedLight);
            this.renderPiece(poseStack,MBS,blockstate.getValue(BedBlock.PART) == BedPart.HEAD ? this.headR : this.footR,blockstate.getValue(BedBlock.FACING),material,combinedLight,combinedOverlay,false);
        } else {
            this.renderPiece(poseStack, MBS, this.headR, Direction.SOUTH, material, combinedLight, combinedOverlay, false);
            this.renderPiece(poseStack, MBS, this.footR, Direction.SOUTH, material, combinedLight, combinedOverlay, true);
        }
    }
    private void renderPiece(
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            ModelPart part,
            Direction direction,
            Material material,
            int combinedLight,
            int combinedOverlay,
            boolean isFoot
    ) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.5625F, isFoot ? -1.0F : 0.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F + direction.toYRot()));
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        VertexConsumer vertexconsumer = material.buffer(bufferSource, RenderType::entitySolid);
        part.render(poseStack, vertexconsumer, combinedLight, combinedOverlay);
        poseStack.popPose();
    }
}
