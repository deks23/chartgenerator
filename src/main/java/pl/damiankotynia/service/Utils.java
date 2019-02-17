package pl.damiankotynia.service;


public class Utils {

    /**
     * Check if given Object is null
     * @param object object to check
     * @return true if object is empty
     */
    public static boolean isNull(Object object){
        if(object==null){
            return true;
        }
        return false;
    }

 /*   public boolean serviceNotEmpty(Service service){
        if(isBlank(service.getCustomerName())&&service.getStartTime()!=null)
            return false;

    }*/

    /**
     * Check if given string contains any chars
     * @param str string to check
     * @return True if string is  empty
     */
    public boolean isBlank(String str){
        if(str==null)
            return false;
        str.replace("\\s", "");
        if ("".equals(str))
           return false;
        return true;
    }

    public static final String DATABASE_LOGGER = "DATABASE \t\t >>>> ";
    public static final String CONNECTION_LOGGER = "CONNECTION \t\t >>>> ";
    public static final String REQUEST_EXECUTOR_LOGGER = "REQUEST_EXE \t >>>> ";
}
