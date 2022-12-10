package  com.denger.naomitian.module.misc;

import com.denger.naomitian.manager.FriendManager;
import com.denger.naomitian.module.Category;
import com.denger.naomitian.module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Mouse;
import com.denger.naomitian.notifications.*;

public class MiddleClick extends Module {

	public MiddleClick() {
		super("MCF", Category.OUTHER);
	}

	@SubscribeEvent
	public void onMiddleClick(InputEvent.MouseInputEvent event) {
		if (mc.player == null || mc.world == null) return;

		if (Mouse.getEventButtonState() && Mouse.getEventButton() == 2) {

			if (mc.objectMouseOver.entityHit instanceof EntityPlayer) clickFriend();


		}
	}

	private void clickFriend() {
		String name = mc.objectMouseOver.entityHit.getName();

		if (FriendManager.isFriend(name)) {
			FriendManager.FRIENDS.remove(name);
			notifications.add(TextFormatting.RED + name, "Remove from Friend list", Type.OK);


		} else {
			FriendManager.FRIENDS.add(name);
			notifications.add(TextFormatting.GREEN + name, "add in Friends list", Type.OK);
		}
	}
}




