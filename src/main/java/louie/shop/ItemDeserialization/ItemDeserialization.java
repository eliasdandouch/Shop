package louie.shop.ItemDeserialization;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class ItemDeserialization {

    public static ItemStack base64ToItemStack(String shopItem) throws IllegalStateException {
        try {
            // Deserialize the item & decode it
            byte[] decodedSerialization = Base64.getDecoder().decode(shopItem);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedSerialization);
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            return (ItemStack) dataInput.readObject();
        } catch (Exception e) {
            throw new IllegalStateException("couldn't decode item", e);
        }
    }
}
