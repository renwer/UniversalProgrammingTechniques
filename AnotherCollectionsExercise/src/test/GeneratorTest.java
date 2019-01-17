package test;

import com.company.generators.CommonGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class GeneratorTest {

    CommonGenerator generator;

    @Before
    public void init() {
        generator = new CommonGenerator();
    }

    @Test
    public void assertDepartametsAreNotEmpty() {
        Assert.assertTrue(generator.departments.size() > 0);
    }

    @Test
    public void assertPeselsAreValid() {
        List<String> allPesels = generator.students
                .stream().map(e -> e._pesel)
                .collect(Collectors.toList());
        allPesels.addAll(generator.teachers
                .stream().map(e -> e._pesel)
                .collect(Collectors.toList()));

        for (String pesel : allPesels) {
            System.out.println(pesel);
            int A = pesel.charAt(0);
            int B = pesel.charAt(1);
            int C = pesel.charAt(2);
            int D = pesel.charAt(3);
            int E = pesel.charAt(4);
            int F = pesel.charAt(5);
            int G = pesel.charAt(6);
            int H = pesel.charAt(7);
            int I = pesel.charAt(8);
            int J = pesel.charAt(9);
            int controlDigit = (9 * A + 7 * B + 3 * C + D + 9 * E + 7 * F + 3 * G + H + 9 * I + 7 * J) % 10;
            System.out.println(controlDigit);
        }
    }
}
