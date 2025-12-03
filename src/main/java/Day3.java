import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day3 {
    public static void main(String[] args) throws IOException {
        List<long[]> banks = Files.lines(Paths.get("src/main/resources/", "Data3.txt"))
                .map(s -> s.chars().map(c -> c - '0').asLongStream().toArray())
                .toList();
        challenge1(banks);
        challenge2(banks,12);

                
    }

    private static void challenge2(List<long[]> banks, int NUM) {
        long total=0;

        for( long[] bank : banks)
        {
            long subtotal =0;
            int lastIndex=0;

            for (int i = NUM-1; i >=0 ; i--) {
                subtotal*=10;
                long max = Arrays.stream(Arrays.copyOfRange(bank, lastIndex, bank.length - i)).max().getAsLong();
                for (int j = lastIndex; j < bank.length; j++) {
                    if(max==bank[j])
                    {
                        lastIndex = j+1;
                        break;
                    }
                }
                subtotal+=max;
            }

            total+=subtotal;
        }
        System.out.println(total);
    }

    private static void challenge1(List<long[]> banks) {

        long total=0;

        for( long[] bank : banks)
        {
            long first = Arrays.stream(Arrays.copyOfRange(bank, 0, bank.length - 1)).max().getAsLong();
            int index = 0;
            for (int i = 0; i < bank.length; i++) {
                if(first==bank[i])
                {
                    index = i;
                    break;
                }
            }
            long second = Arrays.stream(Arrays.copyOfRange(bank, index+1, bank.length )).max().getAsLong();

            total+= first*10 +second;
        }
        System.out.println(total);
    }

}
