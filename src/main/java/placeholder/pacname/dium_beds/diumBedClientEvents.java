package placeholder.pacname.dium_beds;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.client.renderer.blockentity.ShulkerBoxRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class diumBedClientEvents {
    @SubscribeEvent
    public static void renRegEvent (EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(diumBeds.DIUM_BE_T.get(),diumBedRenderer::new);
//        RegistryObject<BlockEntityType<ShulkerBoxBlockEntity>> bbox = null;
        //RegistryObject<BlockEntityType<BedBlockEntity>> bed = null;
        //event.registerBlockEntityRenderer(bbox.get(),ShulkerBoxRenderer::new);
    }
}
