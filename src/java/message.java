
import com.ebaza.tech.controller.AfricasTalkingGateway;
import org.json.JSONArray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Godwin
 */
public class message {

    private static String username = "iyaturemye";
    private static String apiKey = "6cee4a31d2456e8a28d3018acdf71ca36ca8b229448cb308ce31cd0a688063dc";

    public static void main(String[] args) {
        try {
            AfricasTalkingGateway gateway = new AfricasTalkingGateway(username, apiKey);
//            String message = "muraho,umuryango wa Hategekimana Theogene na Munderere Dancilla Unejejwe no kubatumira \n"
//                    + "munama y'ubukwe bwabanababo Uwitonze Aline & Habineza Felix izaba kuri 12/12/2018 at 6:00 PM ikaba izabera sumsung mu ya station yo kwakabuga\n"
//                    + "murakoze.";
            String msg = "Mwiriwe neza,Umuryango wa Hategekima Theogene na Munderere Dancilla wishimiye kubashimira kubwitange mwagize m’ubukwe bwabana babo imana izabahe "
                    + "umugisha kandi mugire umwaka mushya  muhire wa 2019 "
                    + "uzababere uwamahoro n’iterambere murakoze.";
//+250788626935
//+250784660606
//+250788527549
//+250787794730
            String[] numbers = {"+250788527549", "+250788266579", "+250789196241", "+250785382211",
                "+250785559298", "+250789713973", "+250788351336", "+250788494435", "+250783937186", "+250787067324",
                "+250788988551", "+250788323582", "+250788764344", "+250788599893", "+250788893399", "+250788408211",
                "+250788461821", "+250788222272", "+250788533880", "+250788406890", "+250788408303", "+250788468590",
                "+250782531427", "+250788662138", "+250788657943", "+250788411422", "+250789829608", "+250786157633",
                "+250788749222", "+250788662538", "+250788520718", "+250788237100", "+250783260261", "+250787857373",
                "+250788593166", "+250786087442", "+250788207166", "+250783702356", "+250788696943", "+250788438235",
                "+250787680123", "+250788537541", "+250788761135", "+250788834558", "+250788846119", "+250788756964",
                "+250788351282", "+250788499975", "+250786601003", "+250788552984",
                "+250788552783",
                "+250788557151","+250788626935","+250722749222","+250788854325","+250784891248","+2500786601003","+250786476138"};
//                            JSONArray results = gateway.sendMessage("+250789713973", message);

//            for (String n : numbers) {
//                System.out.println(n);
//                JSONArray results = gateway.sendMessage(n, msg);
//            }
            JSONArray results = gateway.sendMessage("+250789713973", msg);

            System.out.println("here we go boss wanjye");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
