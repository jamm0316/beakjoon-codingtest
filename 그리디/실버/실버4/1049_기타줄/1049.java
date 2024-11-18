import java.io.*;
import java.util.*;

public class Main {
    static final double PACKAGE_NUM = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int requiredString = Integer.parseInt(st.nextToken());
        int brandNum = Integer.parseInt(st.nextToken());

        int minPackagePrice = Integer.MAX_VALUE;
        int minIndividualPrice = Integer.MAX_VALUE;

        for (int i = 0; i < brandNum; i++) {
            st = new StringTokenizer(br.readLine());
            int packagePrice = Integer.parseInt(st.nextToken());
            int individualPrice = Integer.parseInt(st.nextToken());
            minPackagePrice = Math.min(minPackagePrice, packagePrice);
            minIndividualPrice = Math.min(minIndividualPrice, individualPrice);
        }

        int requiredFullPackage = (int) Math.ceil(requiredString / PACKAGE_NUM);
        int requiredEachPackage = (int) Math.floor(requiredString / PACKAGE_NUM);

        int costByFullPackage = minPackagePrice * requiredFullPackage;
        int costByPackageAndIndividual = minPackagePrice * requiredEachPackage +
                minIndividualPrice * (requiredString - requiredEachPackage * (int) PACKAGE_NUM);
        int costByIndividual = minIndividualPrice * requiredString;

        System.out.println(Math.min(costByFullPackage, Math.min(costByIndividual, costByPackageAndIndividual)));
    }
}
