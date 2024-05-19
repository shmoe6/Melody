import com.github.shmoe6.melody.core.MelodyConfig
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import net.minecraft.client.Minecraft
import java.net.URL

class LockedSlotsScreen : WindowScreen(ElementaVersion.V5) {

    // <feature, isHidden>
    var elements = ArrayList<Pair<UIImage, Boolean>>()

    init {
        for (i in Minecraft.getMinecraft().thePlayer.inventory.mainInventory.indices) {

            val curItemStack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i]

            elements[i] = Pair(UIImage.ofURL(URL("https://imgur.com/gallery/masterlock-m1xd-2biSBlB"))
                .constrain {
                    x = curItemStack.itemFrame.posX.pixels
                    y = curItemStack.itemFrame.posY.pixels
                    width = curItemStack.itemFrame.width.pixels
                    height = curItemStack.itemFrame.height.pixels
                } childOf this.window, true)
        }

        refreshEnabledElements()
    }

    fun refreshEnabledElements() {
        val lockedSlots = MelodyConfig.lockedSlots.split(Regex("\\s"))

        for (i in elements.indices) {
            if (lockedSlots.contains("$i")) {
                elements[i] = elements[i].copy(second = false)
            }
        }
    }
}