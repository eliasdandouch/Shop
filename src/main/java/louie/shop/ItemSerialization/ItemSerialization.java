package louie.shop.ItemSerialization;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ItemSerialization {

    public static String itemStackToBase64(ItemStack item) throws IllegalStateException {
        try {
            // Output Streams for base64
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // write the object/item
            dataOutput.writeObject(item);

            // Serialize that item
            dataOutput.close();
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("unable to save item", e);
        }
    }
}
