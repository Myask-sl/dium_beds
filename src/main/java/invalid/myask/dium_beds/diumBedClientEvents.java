package invalid.myask.dium_beds;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


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
