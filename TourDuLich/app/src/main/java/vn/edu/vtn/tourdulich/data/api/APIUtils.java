package vn.edu.vtn.tourdulich.data.api;

public class APIUtils {
    private static String baseURL = "http://www.kiemtra.somee.com/";

    public static APIService getServer() {
        return APIClient.getClient(baseURL).create(APIService.class);
    }
}
