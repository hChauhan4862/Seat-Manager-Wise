package wise.utils.helper;
import java.lang.reflect.Field;

public class RequestUtil {
    /**
     * @apiNote It will change the existingData with the new data to be updated.
     * @paramtype <T>
     * @param existingData
     * @param newData
     */
    
    public static <T> void PatchDataModifier(T existingData, Object newData) {
        Class<?> clazz = existingData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                // Look for a matching field in newData's class
                Field newField = newData.getClass().getDeclaredField(field.getName());
                newField.setAccessible(true);
                Object newValue = newField.get(newData);
                
                // Check if the types of the fields are compatible
                if (newValue != null && field.getType().isAssignableFrom(newField.getType())) {
                    field.set(existingData, newValue);  // Set the value if types are compatible
                } else {
                    System.out.println("Type mismatch for field: " + field.getName());
                }
            }
            catch (NoSuchFieldException e) {
                // 
            }
            catch (IllegalAccessException e) {
                // Handle cases where the field doesn't exist or is inaccessible
                e.printStackTrace();
            }
        }
    }
    
}
