
import com.denger.naomitian.Micotian;
import com.denger.naomitian.utils.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import com.denger.naomitian.manager.HWIDManager;

@Mod(modid = Reference.Moded, version = Reference.Version, name = Reference.Name)

public class Main {
    @EventHandler
    public void init(FMLInitializationEvent event) throws Exception {
        HWIDManager.hwidCheck();
    	Micotian.instance = new Micotian();
    	Micotian.instance.init();
    }
}
