import java.util.*;

public class Chat {
    public static void main(String[] args) {

        String[] illegal_replies1 = {"say what? <request>?", "what in the world is <request>?"};
        String[] illegal_replies2 = {"say say <request>!", "dude, what is <request>!?"};
        String[] legal_replies1 = {"say <phrase>? okay: <phrase>", "fine - <phrase>"};
        String[] legal_replies2 = {"alright: <phrase>", "okay, here goes! <phrase>"};

        ChatterBot[] bots = new ChatterBot[2];
        bots[0] = new ChatterBot("Kermit",legal_replies1,  illegal_replies1);
        bots[1] = new ChatterBot("Elmo", legal_replies2, illegal_replies2);

        Scanner scanner = new Scanner(System.in);
        String statement = scanner.nextLine();

        while(true) {
            for(ChatterBot bot : bots) {
                statement = bot.replyTo(statement);
                System.out.print(bot.getName() + ": " + statement);
                scanner.nextLine();  // wait for "enter" before continuing
            }
        }
    }
}

