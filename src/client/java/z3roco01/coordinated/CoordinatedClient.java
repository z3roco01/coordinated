package z3roco01.coordinated;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class CoordinatedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        HudElementRegistry.addLast(Identifier.of("coordinated:coords"), (context, tickCounter) -> {
            TextRenderer tr = MinecraftClient.getInstance().textRenderer;
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            // create position text
            BlockPos pos = player.getBlockPos();
            String posText = "Pos: " + pos.getX() + " " + pos.getY() + " " + pos.getZ();

            // do facing stuff
            Direction facing = player.getHorizontalFacing();
            String facingText = "F: " + facing.toString() + " [" + facing.getAxis().asString().toUpperCase();
            if(facing == Direction.NORTH || facing == Direction.WEST)
                facingText += "-";

            facingText += "]";

            // get biome
            String biomeText = "B: " + player.getEntityWorld().getBiome(pos).getIdAsString().split(":", 2)[1];

            // render it up
            context.drawText(tr, posText, 2, 2, -1, true);
            context.drawText(tr, facingText, 2, 2+tr.fontHeight, -1, true);
            context.drawText(tr, biomeText, 2, 2+tr.fontHeight*2, -1, true);
        });
	}
}