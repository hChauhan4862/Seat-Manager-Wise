package wise.utils;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wise.controllers.web.user_management.DepartmentController;

public class RequestUtil {
    /**
     * @apiNote It will change the existingData with the new data to be updated.
     * @paramtype <T>
     * @param existingData
     * @param newData
     */

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    
    public static <T> void PatchDataModifier(T existingData, Object newData, boolean skipNull) {
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
                if ((newValue != null || !skipNull) && field.getType().isAssignableFrom(newField.getType())) {
                    field.set(existingData, newValue);  // Set the value if types are compatible
                } else {
                    System.out.println("Type mismatch for field: " + field.getName());
                }
            }
            catch (NoSuchFieldException e) {
                logger.error("Field not found in newData: {}", field.getName(), e);
            } catch (IllegalAccessException e) {
                logger.error("Unable to access field: {}", field.getName(), e);
            }            
        }
    }



    public static <T> T NewPatchDataModifier(Class<T> clazz, Object newData, boolean skipNull) {

        T existingData = null;

        try {
            // Instantiate a new object of the provided class
            existingData = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if object creation fails
        }

        PatchDataModifier(existingData, newData, skipNull); // Call the PatchDataModifier method to update the object

        return existingData;
    }

    public static <T> T NewPatchDataModifier(Class<T> clazz, Object newData) {
        return NewPatchDataModifier(clazz, newData, false);
    }

    public static <T> void PatchDataModifier(T existingData, Object newData) {
        PatchDataModifier(existingData, newData, false);
    }
}
