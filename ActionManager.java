import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActionManager {
    private static final String[] actions = {"upgrade", "move", "take role", "act", "rehearse", "end turn", "end game"};
    private static final HashMap<String, Method> actionRelevance = populateActionRelevance();
    private static final HashMap<String, Method> actionExecution = populateActionExecution();
    private static final String[] turnEndingActions = {"act", "rehearse", "take role"};


    private static HashMap<String, Method> populateActionExecution() {
        try {
            HashMap<String, Method> actionExecution = new HashMap<>();
            actionExecution.put("upgrade", Player.class.getMethod("upgrade", Controller.class));
            actionExecution.put("move", Player.class.getMethod("move", Controller.class));
            actionExecution.put("take role", Player.class.getMethod("takeRole", Controller.class));
            actionExecution.put("act", Player.class.getMethod("act", Controller.class));
            actionExecution.put("rehearse", Player.class.getMethod("rehearse", Controller.class));
            actionExecution.put("end turn", Player.class.getMethod("endTurn", Controller.class));

            return actionExecution;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HashMap<String, Method> populateActionRelevance() {
        try {
            HashMap<String, Method> actionRelevance = new HashMap<>();
            actionRelevance.put("upgrade", Player.class.getMethod("canUpgrade"));
            actionRelevance.put("move", Player.class.getMethod("canMove"));
            actionRelevance.put("take role", Player.class.getMethod("canTakeRole"));
            actionRelevance.put("act", Player.class.getMethod("canAct"));
            actionRelevance.put("rehearse", Player.class.getMethod("canRehearse"));
            actionRelevance.put("end turn", Player.class.getMethod("canEndTurn"));
            actionRelevance.put("end game", Player.class.getMethod("canEndGame"));

            return actionRelevance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> getPossibleActions(Player player, ArrayList<String> actionsTaken) {
        if (actionsTaken.contains("end turn")) {
            return new ArrayList<String>();
        }

        for (String action: actionsTaken) {
            if (actionEndsTurn(action)) {
                // the action ends the turn, only end turn and end game are available
                return new ArrayList<>(List.of("end turn", "end game"));
            }
        }

        ArrayList<String> possibleActions = new ArrayList<>();
        assert actionRelevance != null;

        try {
            for (String action: actions) {
                if (actionsTaken.contains(action)) {
                    // this action has already been taken, so it can't be taken again
                    continue;
                }
                if (actionRelevance.get(action).invoke(player).equals(true)) {
                    possibleActions.add(action);
                }
            }

            return possibleActions;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean actionEndsTurn(String action) {
        for (String turnEndingAction: turnEndingActions) {
            if (turnEndingAction.equals(action)) {
                return true;
            }
        }

        return false;
    }

    public static void executeAction(Player player, String action, Controller controller) {
        try {
            assert actionExecution != null;
            actionExecution.get(action).invoke(player, controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
