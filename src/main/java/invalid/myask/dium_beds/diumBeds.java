package invalid.myask.dium_beds;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(diumBeds.MODID)
public class diumBeds
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "dium_beds";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "dium_beds" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    // Creates a new Block with the id "dium_beds:example_block", combining the namespace and path
//    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    
    public static final RegistryObject<Block> WHITE_DIUM_BED = BLOCKS.register("white_dium_bed", () -> dium_bed(DyeColor.WHITE));
    public static final RegistryObject<Block> ORANGE_DIUM_BED = BLOCKS.register("orange_dium_bed", () -> dium_bed(DyeColor.ORANGE));
    public static final RegistryObject<Block> MAGENTA_DIUM_BED = BLOCKS.register("magenta_dium_bed", () -> dium_bed(DyeColor.MAGENTA));
    public static final RegistryObject<Block> LIGHT_BLUE_DIUM_BED = BLOCKS.register("light_blue_dium_bed", () -> dium_bed(DyeColor.LIGHT_BLUE));
    public static final RegistryObject<Block> YELLOW_DIUM_BED = BLOCKS.register("yellow_dium_bed", () -> dium_bed(DyeColor.YELLOW));
    public static final RegistryObject<Block> LIME_DIUM_BED = BLOCKS.register("lime_dium_bed", () -> dium_bed(DyeColor.LIME));
    public static final RegistryObject<Block> PINK_DIUM_BED = BLOCKS.register("pink_dium_bed", () -> dium_bed(DyeColor.PINK));
    public static final RegistryObject<Block> GRAY_DIUM_BED = BLOCKS.register("gray_dium_bed", () -> dium_bed(DyeColor.GRAY));
    public static final RegistryObject<Block> LIGHT_GRAY_DIUM_BED = BLOCKS.register("light_gray_dium_bed", () -> dium_bed(DyeColor.LIGHT_GRAY));
    public static final RegistryObject<Block> CYAN_DIUM_BED = BLOCKS.register("cyan_dium_bed", () -> dium_bed(DyeColor.CYAN));
    public static final RegistryObject<Block> PURPLE_DIUM_BED = BLOCKS.register("purple_dium_bed", () -> dium_bed(DyeColor.PURPLE));
    public static final RegistryObject<Block> BLUE_DIUM_BED = BLOCKS.register("blue_dium_bed", () -> dium_bed(DyeColor.BLUE));
    public static final RegistryObject<Block> BROWN_DIUM_BED = BLOCKS.register("brown_dium_bed", () -> dium_bed(DyeColor.BROWN));
    public static final RegistryObject<Block> GREEN_DIUM_BED = BLOCKS.register("green_dium_bed", () -> dium_bed(DyeColor.GREEN));
    public static final RegistryObject<Block> RED_DIUM_BED = BLOCKS.register("red_dium_bed", () -> dium_bed(DyeColor.RED));
    public static final RegistryObject<Block> BLACK_DIUM_BED = BLOCKS.register("black_dium_bed", () -> dium_bed(DyeColor.BLACK));

    public static final RegistryObject<Block> BEDDIUM_DIUM_BED = BLOCKS.register("beddium_dium_bed", () -> dium_bed(null));

    public static final RegistryObject<Block> AQUA_DIUM_BED = BLOCKS.register("aqua_dium_bed", () -> dium_bed(DyeColor.CYAN,true));

    public static final RegistryObject<BlockEntityType<diumBedBlockEntity>> DIUM_BE_T = BLOCK_ENTITY_TYPES.register("dium_be",
            () -> BlockEntityType.Builder.of(diumBedBlockEntity::new,
                    WHITE_DIUM_BED.get(),
                    ORANGE_DIUM_BED.get(),
                    MAGENTA_DIUM_BED.get(),
                    LIGHT_BLUE_DIUM_BED.get(),
                    YELLOW_DIUM_BED.get(),
                    LIME_DIUM_BED.get(),
                    PINK_DIUM_BED.get(),
                    GRAY_DIUM_BED.get(),
                    LIGHT_GRAY_DIUM_BED.get(),
                    CYAN_DIUM_BED.get(),
                    PURPLE_DIUM_BED.get(),
                    BLUE_DIUM_BED.get(),
                    BROWN_DIUM_BED.get(),
                    GREEN_DIUM_BED.get(),
                    RED_DIUM_BED.get(),
                    BEDDIUM_DIUM_BED.get(),
                    AQUA_DIUM_BED.get(),
                    BLACK_DIUM_BED.get() ).build(null));


    private static Block dium_bed(DyeColor dyeColor, boolean stupidSpecialCase) {
        MapColor mc;
        if (stupidSpecialCase)
            mc = MapColor.TERRACOTTA_CYAN;
        else
            mc = (dyeColor == null) ? DyeColor.LIGHT_BLUE.getMapColor(): dyeColor.getMapColor(); //specialcase the Beddiumbed
        return new diumBedBlock(
                dyeColor,
                BlockBehaviour.Properties.of()
                        .mapColor(p -> p.getValue(BedBlock.PART) == BedPart.FOOT ? mc : MapColor.WOOL)
                        .sound(SoundType.WOOD)
                        .strength(0.2F)
                        .noOcclusion()
                        .requiresCorrectToolForDrops()
                        .ignitedByLava()
                        .pushReaction(PushReaction.DESTROY),
                stupidSpecialCase
        );
    }
    private static Block dium_bed(DyeColor dyeColor) { return dium_bed(dyeColor, false);}
    public static final RegistryObject<Item> ITEM_WHITE_DIUM_BED = ITEMS.register("white_dium_bed", () -> new BedItem(WHITE_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_ORANGE_DIUM_BED = ITEMS.register("orange_dium_bed", () -> new BedItem(ORANGE_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_MAGENTA_DIUM_BED = ITEMS.register("magenta_dium_bed", () ->new BedItem(MAGENTA_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_LIGHT_BLUE_DIUM_BED = ITEMS.register("light_blue_dium_bed", () ->new BedItem(LIGHT_BLUE_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_YELLOW_DIUM_BED = ITEMS.register("yellow_dium_bed", () ->new BedItem(YELLOW_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_LIME_DIUM_BED = ITEMS.register("lime_dium_bed", () ->new BedItem(LIME_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_PINK_DIUM_BED = ITEMS.register("pink_dium_bed", () ->new BedItem(PINK_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_GRAY_DIUM_BED = ITEMS.register("gray_dium_bed", () ->new BedItem(GRAY_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_LIGHT_GRAY_DIUM_BED = ITEMS.register("light_gray_dium_bed", () ->new BedItem(LIGHT_GRAY_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_CYAN_DIUM_BED = ITEMS.register("cyan_dium_bed", () ->new BedItem(CYAN_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_PURPLE_DIUM_BED = ITEMS.register("purple_dium_bed", () ->new BedItem(PURPLE_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_BLUE_DIUM_BED = ITEMS.register("blue_dium_bed", () ->new BedItem(BLUE_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_BROWN_DIUM_BED = ITEMS.register("brown_dium_bed", () ->new BedItem(BROWN_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_GREEN_DIUM_BED = ITEMS.register("green_dium_bed", () ->new BedItem(GREEN_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_RED_DIUM_BED = ITEMS.register("red_dium_bed", () ->new BedItem(RED_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_BLACK_DIUM_BED = ITEMS.register("black_dium_bed", () ->new BedItem(BLACK_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_BEDDIUM_DIUM_BED = ITEMS.register("beddium_dium_bed", () ->new BedItem(BEDDIUM_DIUM_BED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ITEM_AQUA_DIUM_BED = ITEMS.register("aqua_dium_bed", () ->new BedItem(AQUA_DIUM_BED.get(), new Item.Properties().stacksTo(1)));

    // Creates a creative tab
    public static final RegistryObject<CreativeModeTab> DIUMBEDS_TAB = CREATIVE_MODE_TABS.register("diumbeds_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ITEM_BEDDIUM_DIUM_BED.get().getDefaultInstance())
            .title(Component.translatable("itemGroup.diumbeds_tab"))
            .displayItems((parameters, output) -> {
//                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(ITEM_BLACK_DIUM_BED.get());
                output.accept(ITEM_BLUE_DIUM_BED.get());
                output.accept(ITEM_GREEN_DIUM_BED.get());
                output.accept(ITEM_CYAN_DIUM_BED.get());
                output.accept(ITEM_RED_DIUM_BED.get());
                output.accept(ITEM_PURPLE_DIUM_BED.get());
                output.accept(ITEM_BROWN_DIUM_BED.get());
                output.accept(ITEM_LIGHT_GRAY_DIUM_BED.get());
                output.accept(ITEM_GRAY_DIUM_BED.get());
                output.accept(ITEM_LIGHT_BLUE_DIUM_BED.get());
                output.accept(ITEM_LIME_DIUM_BED.get());
                output.accept(ITEM_ORANGE_DIUM_BED.get());
                output.accept(ITEM_PINK_DIUM_BED.get());
                output.accept(ITEM_MAGENTA_DIUM_BED.get());
                output.accept(ITEM_YELLOW_DIUM_BED.get());
                output.accept(ITEM_WHITE_DIUM_BED.get());
                output.accept(ITEM_AQUA_DIUM_BED.get());
                output.accept(ITEM_BEDDIUM_DIUM_BED.get());
            }).build());

    public diumBeds()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);//and fucking BETs, can't forget 'em.

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
/*
        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));*/
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
        @SubscribeEvent
        public static void onRenderRegister(EntityRenderersEvent event)
        {

        }
    }
}
