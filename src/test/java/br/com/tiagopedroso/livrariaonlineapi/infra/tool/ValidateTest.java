package br.com.tiagopedroso.livrariaonlineapi.infra.tool;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ValidateTest {

    /*------------------------------+
    |  stringIsBlank(String value)  |
    +-------------------------------+
    */

    @Test
    void stringIsBlank_com_null_Entao_retornar_true() throws Exception {
        //Dado
        String value = null;

        //Quando
        boolean response = Validate.stringIsBlank(value);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringIsBlank_com_vazio_Entao_retornar_true() throws Exception {
        //Dado
        String value = "";

        //Quando
        boolean response = Validate.stringIsBlank(value);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringIsBlank_com_espacos_Entao_retornar_true() throws Exception {
        //Dado
        String value = "      ";

        //Quando
        boolean response = Validate.stringIsBlank(value);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringIsBlank_com_texto_Entao_retornar_false() throws Exception {
        //Dado
        String value = "texto";

        //Quando
        boolean response = Validate.stringIsBlank(value);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringIsBlank_com_texto_com_espacos_Entao_retornar_false() throws Exception {
        //Dado
        String value = "    texto  ";

        //Quando
        boolean response = Validate.stringIsBlank(value);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*-------------------------------+
    |  stringIsFilled(String value)  |
    +--------------------------------+
    */

    @Test
    void stringIsFilled_com_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;

        //Quando
        boolean response = Validate.stringIsFilled(value);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringIsFilled_com_vazio_Entao_retornar_false() throws Exception {
        //Dado
        String value = "";

        //Quando
        boolean response = Validate.stringIsFilled(value);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringIsFilled_com_texto_Entao_retornar_true() throws Exception {
        //Dado
        String value = "texto";

        //Quando
        boolean response = Validate.stringIsFilled(value);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringIsFilled_com_texto_com_espacos_Entao_retornar_true() throws Exception {
        //Dado
        String value = "   tex  to   ";

        //Quando
        boolean response = Validate.stringIsFilled(value);

        //Então
        assertThat(response, is(equalTo(true)));
    }


    /*------------------------------------+
    |  stringsAreBlank(String... values)  |
    +-------------------------------------+
    */

    @Test
    void stringsAreBlank_com_tres_valuees_null_Entao_retornar_true() throws Exception {
        //Dado
        String value1 = null;
        String value2 = null;
        String value3 = null;

        //Quando
        boolean response = Validate.stringsAreBlank(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringsAreBlank_com_tres_valuees_vazios_Entao_retornar_true() throws Exception {
        //Dado
        String value1 = "";
        String value2 = "";
        String value3 = "";

        //Quando
        boolean response = Validate.stringsAreBlank(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringsAreBlank_com_tres_valuees_preenchidos_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = "texto1";
        String value2 = "texto2";
        String value3 = "texto3";

        //Quando
        boolean response = Validate.stringsAreBlank(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringsAreBlank_com_dois_valuees_vazios_e_um_null_Entao_retornar_true() throws Exception {
        //Dado
        String value1 = "";
        String value2 = null;
        String value3 = "";

        //Quando
        boolean response = Validate.stringsAreBlank(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringsAreBlank_com_dois_valuees_vazios_e_um_texto_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = "";
        String value2 = "texto";
        String value3 = "";

        //Quando
        boolean response = Validate.stringsAreBlank(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringsAreBlank_com_um_vazio_um_null_e_um_texto_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = "";
        String value2 = null;
        String value3 = "texto";

        //Quando
        boolean response = Validate.stringsAreBlank(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*-------------------------------------+
    |  stringsAreFilled(String... values)  |
    +--------------------------------------+
    */

    @Test
    void stringsAreFilled_com_tres_valuees_null_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = null;
        String value2 = null;
        String value3 = null;

        //Quando
        boolean response = Validate.stringsAreFilled(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringsAreFilled_com_tres_valuees_vazios_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = "";
        String value2 = "";
        String value3 = "";

        //Quando
        boolean response = Validate.stringsAreFilled(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringsAreFilled_com_tres_valuees_preenchidos_Entao_retornar_true() throws Exception {
        //Dado
        String value1 = "texto1";
        String value2 = "texto2";
        String value3 = "texto3";

        //Quando
        boolean response = Validate.stringsAreFilled(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(true)));
    }


    @Test
    void stringsAreFilled_com_dois_valuees_vazios_e_um_null_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = "";
        String value2 = null;
        String value3 = "";

        //Quando
        boolean response = Validate.stringsAreFilled(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringsAreFilled_com_dois_valuees_vazios_e_um_texto_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = "";
        String value2 = "texto";
        String value3 = "";

        //Quando
        boolean response = Validate.stringsAreFilled(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringsAreFilled_com_um_vazio_um_null_e_um_texto_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = "";
        String value2 = null;
        String value3 = "texto";

        //Quando
        boolean response = Validate.stringsAreFilled(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringsAreFilled_com_dois_preenchidos_e_um_vazio_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = "texto1";
        String value2 = "";
        String value3 = "texto2";

        //Quando
        boolean response = Validate.stringsAreFilled(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*-------------------------------------------------------------------+
    |  stringObjectsAreEqual(Object mainObject, Object... otherObjects)  |
    +--------------------------------------------------------------------+
    */

    @Test
    void stringObjectsAreEqual_com_valuees_iguais_Entao_retornar_true() throws Exception {
        //Dado
        var value1 = "a";
        var value2 = "a";
        var value3 = "a";

        //Quando
        boolean response = Validate.stringObjectsAreEqual(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectsAreEqual_com_objetos_com_toString_iguais_Entao_retornar_true() throws Exception {
        //Dado
        var value1 = new BigDecimal("10.0");
        var value2 = new BigDecimal("10.0");
        var value3 = new BigDecimal("10.0");

        //Quando
        boolean response = Validate.stringObjectsAreEqual(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectsAreEqual_com_valuees_diferentes_Entao_retornar_false() throws Exception {
        //Dado
        var value1 = "a";
        var value2 = "a ";
        var value3 = "a  ";

        //Quando
        boolean response = Validate.stringObjectsAreEqual(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectsAreEqual_com_value_texto_e_null_texto_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = "a";
        String value2 = null;
        String value3 = "a";

        //Quando
        boolean response = Validate.stringObjectsAreEqual(value1, value2, value3);
        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectsAreEqual_com_value_objeto_e_dois_null_Entao_retornar_false() throws Exception {
        //Dado
        BigDecimal value1 = new BigDecimal("10");
        BigDecimal value2 = null;
        BigDecimal value3 = null;

        //Quando
        boolean response = Validate.stringObjectsAreEqual(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectsAreEqual_com_objetos_com_toString_diferentes_Entao_retornar_false() throws Exception {
        //Dado
        BigDecimal value1 = new BigDecimal("10");
        BigDecimal value2 = new BigDecimal("10.0");
        BigDecimal value3 = new BigDecimal("10.00");

        //Quando
        boolean response = Validate.stringObjectsAreEqual(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectsAreEqual_com_valuees_null_Entao_retornar_false() throws Exception {
        //Dado
        String value1 = null;
        String value2 = null;
        String value3 = null;

        //Quando
        boolean response = Validate.stringObjectsAreEqual(value1, value2, value3);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*---------------------------------------------------------------------------+
    |  stringObjectIsEqualAtLeastOne(Object object, String... comparisonValues)  |
    +----------------------------------------------------------------------------+
    */

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_iqual_a_um_dos_validores_Entao_retornar_true() throws Exception {
        //Dado
        String value = "a";
        String comparisonValue1 = "b";
        String comparisonValue2 = "aa";
        String comparisonValue3 = "a";

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_iqual_a_um_dos_validores_porem_um_sendo_null_Entao_retornar_true() throws Exception {
        //Dado
        String value = "a";
        String comparisonValue1 = "b";
        String comparisonValue2 = null;
        String comparisonValue3 = "a";

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_iqual_a_dois_validores_Entao_retornar_true() throws Exception {
        //Dado
        String value = "a";
        String comparisonValue1 = "b";
        String comparisonValue2 = "a";
        String comparisonValue3 = "a";

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_preenchido_e_validores_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = "a";
        String comparisonValue1 = null;
        String comparisonValue2 = null;
        String comparisonValue3 = null;

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_preenchido_e_validores_vazios_Entao_retornar_false() throws Exception {
        //Dado
        String value = "a";
        String comparisonValue1 = "";
        String comparisonValue2 = "";
        String comparisonValue3 = "";

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_preenchido_e_validores_divergentes_Entao_retornar_false() throws Exception {
        //Dado
        String value = "a";
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = "aa";

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_null_e_validores_divergentes_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = "aa";

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_null_e_validores_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;
        String comparisonValue1 = null;
        String comparisonValue2 = null;
        String comparisonValue3 = null;

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_null_e_validores_divergentes_com_um_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = null;

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectIsEqualAtLeastOne_com_value_object_e_validores_divergentes_Entao_retornar_false() throws Exception {
        //Dado
        var value = Integer.class;
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = "aa";

        //Quando
        boolean response = Validate.stringObjectIsEqualAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*----------------------------------------------------------------------------+
    |  stringObjectContainsAtLeastOne(Object object, String... comparisonValues)  |
    +-----------------------------------------------------------------------------+
    */

    @Test
    void stringObjectContainsAtLeastOne_com_value_iqual_a_um_dos_validores_Entao_retornar_true() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = "casa";
        String comparisonValue2 = "Rosa";
        String comparisonValue3 = "com";

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectContainsAtLeastOne_com_value_iqual_a_um_dos_validores_porem_um_sendo_null_Entao_retornar_true() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = null;
        String comparisonValue2 = "Rosa";
        String comparisonValue3 = "com";

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectContainsAtLeastOne_com_value_iqual_a_dois_validores_Entao_retornar_true() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = "Com";
        String comparisonValue2 = "Rosa";
        String comparisonValue3 = "com";

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectContainsAtLeastOne_com_value_preenchido_e_validores_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = null;
        String comparisonValue2 = null;
        String comparisonValue3 = null;

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectContainsAtLeastOne_com_value_preenchido_e_validores_vazios_Entao_retornar_false() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = "";
        String comparisonValue2 = "";
        String comparisonValue3 = "";

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectContainsAtLeastOne_com_value_preenchido_e_validores_divergentes_Entao_retornar_false() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = "aa";

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectContainsAtLeastOne_com_value_null_e_validores_divergentes_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = "aa";

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectContainsAtLeastOne_com_value_null_e_validores_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;
        String comparisonValue1 = null;
        String comparisonValue2 = null;
        String comparisonValue3 = null;

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectContainsAtLeastOne_com_value_null_e_validores_divergentes_com_um_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = null;

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectContainsAtLeastOne_com_value_object_e_validores_divergentes_Entao_retornar_false() throws Exception {
        //Dado
        var value = new BigDecimal("10");
        String comparisonValue1 = "bbb";
        String comparisonValue2 = "ccc";
        String comparisonValue3 = "aaa";

        //Quando
        boolean response = Validate.stringObjectContainsAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*------------------------------------------------------------------------------+
    |  stringObjectStartsWithAtLeastOne(Object object, String... comparisonValues)  |
    +-------------------------------------------------------------------------------+
    */

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_iqual_a_um_dos_validores_Entao_retornar_true() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = "casa";
        String comparisonValue2 = "pepino";
        String comparisonValue3 = "com";

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_iqual_a_um_dos_validores_porem_um_sendo_null_Entao_retornar_true() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = null;
        String comparisonValue2 = "pepino";
        String comparisonValue3 = "com";

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_iqual_a_dois_validores_Entao_retornar_true() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = "Com";
        String comparisonValue2 = "pepino";
        String comparisonValue3 = "pep";

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_preenchido_e_validores_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = null;
        String comparisonValue2 = null;
        String comparisonValue3 = null;

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_preenchido_e_validores_vazios_Entao_retornar_false() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = "";
        String comparisonValue2 = "";
        String comparisonValue3 = "";

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_preenchido_e_validores_divergentes_Entao_retornar_false() throws Exception {
        //Dado
        String value = "pepinoDoMarRosaComBordas";
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = "a";

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_null_e_validores_divergentes_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = "a";

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_null_e_validores_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;
        String comparisonValue1 = null;
        String comparisonValue2 = null;
        String comparisonValue3 = null;

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_null_e_validores_divergentes_com_um_null_Entao_retornar_false() throws Exception {
        //Dado
        String value = null;
        String comparisonValue1 = "b";
        String comparisonValue2 = "c";
        String comparisonValue3 = null;

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void stringObjectStartsWithAtLeastOne_com_value_object_e_validores_divergentes_Entao_retornar_false() throws Exception {
        //Dado
        var value = new BigDecimal("10");
        String comparisonValue1 = "bbb";
        String comparisonValue2 = "ccc";
        String comparisonValue3 = "aaa";

        //Quando
        boolean response = Validate.stringObjectStartsWithAtLeastOne(value, comparisonValue1, comparisonValue2, comparisonValue3);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*------------------------------------------------------------+
    |  listContainsAtLeastOneValidElement(Collection collection)  |
    +-------------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneValidElement_com_List_valido_Entao_retornar_true() throws Exception {
        //Dado
        var lista = Arrays.asList(null, "a", "b");

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(lista);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_List_invalido_Entao_retornar_false() throws Exception {
        //Dado
        var lista = Arrays.asList(null, null);

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(lista);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_ArrayList_instanciado_Entao_retornar_false() throws Exception {
        //Dado
        var lista = new ArrayList<>();

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(lista);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_List_null_Entao_retornar_false() throws Exception {
        //Dado
        List<String> lista = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(lista);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*---------------------------------------------------+
    |  mapContainsAtLeastOneValidElement(Map<?, ?> map)  |
    +----------------------------------------------------+
    */

    @Test
    void mapContainsAtLeastOneValidElement_com_Map_valido_Entao_retornar_true() throws Exception {
        //Dado
        Map<String, Object> map = new HashMap<>();
        map.put(null, null);
        map.put("chave1", null);
        map.put("chave2", "a");
        map.put("chave3", 3);

        System.out.println("listContainsAtLeastOneValidElement_com_Map_valido_Entao_retornar_true");
        System.out.println("Elementos: " + map + "\n");

        //Quando
        boolean response = Validate.mapContainsAtLeastOneValidElement(map);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void mapContainsAtLeastOneValidElement_com_Map_invalido_Entao_retornar_false() throws Exception {
        //Dado
        Map<String, Object> map = new HashMap<>();
        map.put(null, null);
        map.put("chave1", null);
        map.put("chave2", null);
        map.put("chave3", null);

        System.out.println("listContainsAtLeastOneValidElement_com_Map_invalido_Entao_retornar_false");
        System.out.println("Elementos: " + map + "\n");

        //Quando
        boolean response = Validate.mapContainsAtLeastOneValidElement(map);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void mapContainsAtLeastOneValidElement_com_Map_instanciado_Entao_retornar_false() throws Exception {
        //Dado
        Map<String, Object> map = new HashMap<>();

        //Quando
        boolean response = Validate.mapContainsAtLeastOneValidElement(map);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void mapContainsAtLeastOneValidElement_com_Map_null_Entao_retornar_false() throws Exception {
        //Dado
        Map<String, Object> map = null;

        //Quando
        boolean response = Validate.mapContainsAtLeastOneValidElement(map);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*------------------------------------------------------+
    |  listContainsAtLeastOneElement(int[] primitiveArray)  |
    +-------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneElement_com_array_de_int_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new int[]{1, 2, 3};

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneElement_com_array_de_int_instanciado_Entao_retornar_true() throws Exception {
        //Dado
        var array = new int[3];

        System.out.println("listContainsAtLeastOneElement_com_array_de_int_instanciado_Entao_retornar_true");
        System.out.print("Elementos: ");
        for (var elemento : array) {
            System.out.print(elemento);
            System.out.print(',');
        }
        System.out.println("\n");

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneElement_com_array_de_int_null_Entao_retornar_false() throws Exception {
        //Dado
        int[] array = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*------------------------------------------------------------+
    |  listContainsAtLeastOneValidElement(Integer[] objectArray)  |
    +-------------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Integer_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new Integer[]{1, 2, 3};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Integer_com_um_elemento_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new Integer[]{null, null, 3};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Integer_instanciado_Entao_retornar_false() throws Exception {
        //Dado
        var array = new Integer[3];

        System.out.println("listContainsAtLeastOneValidElement_com_array_de_Integer_instanciado_Entao_retornar_false");
        System.out.print("Elementos: ");
        for (var elemento : array) {
            System.out.print(elemento);
            System.out.print(',');
        }
        System.out.println("\n");

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Integer_null_Entao_retornar_false() throws Exception {
        //Dado
        Integer[] array = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*-------------------------------------------------------+
    |  listContainsAtLeastOneElement(long[] primitiveArray)  |
    +--------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneElement_com_array_de_long_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new long[]{1, 2, 3};

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneElement_com_array_de_long_instanciado_Entao_retornar_true() throws Exception {
        //Dado
        var array = new long[3];

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneElement_com_array_de_long_null_Entao_retornar_false() throws Exception {
        //Dado
        long[] array = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*---------------------------------------------------------+
    |  listContainsAtLeastOneValidElement(Long[] objectArray)  |
    +----------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Long_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new Long[]{1L, 2L, 3L};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Long_com_um_elemento_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new Long[]{null, null, 3L};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Long_instanciado_Entao_retornar_false() throws Exception {
        //Dado
        var array = new Long[3];

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Long_null_Entao_retornar_false() throws Exception {
        //Dado
        Long[] array = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*--------------------------------------------------------+
    |  listContainsAtLeastOneElement(float[] primitiveArray)  |
    +---------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneElement_com_array_de_float_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new float[]{.0f, .1f, .2f};

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneElement_com_array_de_float_instanciado_Entao_retornar_true() throws Exception {
        //Dado
        var array = new float[3];

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneElement_com_array_de_float_null_Entao_retornar_false() throws Exception {
        //Dado
        float[] array = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*----------------------------------------------------------+
    |  listContainsAtLeastOneValidElement(Float[] objectArray)  |
    +-----------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Float_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new Float[]{2.0f, .1f, .2f};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Float_com_um_elemento_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new Float[]{null, null, .2f};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Float_instanciado_Entao_retornar_false() throws Exception {
        //Dado
        var array = new Float[3];

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Float_null_Entao_retornar_false() throws Exception {
        //Dado
        Float[] array = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*---------------------------------------------------------+
    |  listContainsAtLeastOneElement(double[] primitiveArray)  |
    +----------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneElement_com_array_de_double_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new double[]{.0, .1, .2};

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneElement_com_array_de_double_instanciado_Entao_retornar_true() throws Exception {
        //Dado
        var array = new double[3];

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneElement_com_array_de_double_null_Entao_retornar_false() throws Exception {
        //Dado
        double[] array = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*-----------------------------------------------------------+
    |  listContainsAtLeastOneValidElement(Double[] objectArray)  |
    +------------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Double_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new Double[]{.0, .1, .2};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Double_com_um_elemento_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new Double[]{null, null, .2};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Double_instanciado_Entao_retornar_false() throws Exception {
        //Dado
        var array = new Double[3];

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_Double_null_Entao_retornar_false() throws Exception {
        //Dado
        Double[] array = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }


    /*-----------------------------------------------------------+
    |  listContainsAtLeastOneValidElement(String[] objectArray)  |
    +------------------------------------------------------------+
    */

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_String_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new String[]{"", "a", " "};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_String_com_um_elemento_valido_Entao_retornar_true() throws Exception {
        //Dado
        var array = new String[]{null, null, "a"};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(true)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_String_com_dois_elementos_vazios_e_um_null_Entao_retornar_false() throws Exception {
        //Dado
        var array = new String[]{"", "   ", null};

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_String_com_elementos_vazios_Entao_retornar_false() throws Exception {
        //Dado
        var array = new String[]{"", "", ""};

        System.out.println("listContainsAtLeastOneValidElement_com_array_de_String_com_elementos_vazios_Entao_retornar_false");
        System.out.print("Elementos: ");
        for (var elemento : array) {
            System.out.print(elemento);
            System.out.print(',');
        }
        System.out.println("\n");

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_String_instanciado_Entao_retornar_false() throws Exception {
        //Dado
        var array = new String[3];

        System.out.println("listContainsAtLeastOneValidElement_com_array_de_String_instanciado_Entao_retornar_false");
        System.out.print("Elementos: ");
        for (var elemento : array) {
            System.out.print(elemento);
            System.out.print(',');
        }
        System.out.println("\n");

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }

    @Test
    void listContainsAtLeastOneValidElement_com_array_de_String_null_Entao_retornar_false() throws Exception {
        //Dado
        String[] array = null;

        //Quando
        boolean response = Validate.listContainsAtLeastOneValidElement(array);

        //Então
        assertThat(response, is(equalTo(false)));
    }


}
