import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class ActionManager {
    private static final String[] actions = {"upgrade", "move", "take role", "act", "rehearse"};
    private static final HashMap<String, Method> actionRelevance = populateActionRelevance();
    private static final HashMap<String, Method> actionExecution = populateActionExecution();
    private static final String[] turnEndingActions = {"act", "rehearse"};


    private static HashMap<String, Method> populateActionExecution() {
        try {
            HashMap<String, Method> actionRelevance = new HashMap<>();
            actionRelevance.put("upgrade", Player.class.getMethod("upgrade", Controller.class));
            actionRelevance.put("move", Player.class.getMethod("move", Controller.class));
            actionRelevance.put("take role", Player.class.getMethod("takeRole", Controller.class));
            actionRelevance.put("act", Player.class.getMethod("act", Controller.class));
            actionRelevance.put("rehearse", Player.class.getMethod("rehearse", Controller.class));

            return actionRelevance;
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

            return actionRelevance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> getPossibleActions(Player player, ArrayList<String> actionsTaken) {
        for (String action: actionsTaken) {
            if (actionEndsTurn(action)) {
                // the action ends the turn, so no more actions available
                return new ArrayList<String>();
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

    public static ArrayList<String> getPossibleActions(ArrayList<String> prevPossActions, String executedAction) {
        ArrayList<String> possibleActions = prevPossActions;

        for (String turnEndingAction: turnEndingActions) {
            // if the executed action should end the turn, there are no more possible actions
            if (turnEndingAction.equals(executedAction)) {
                return new ArrayList<String>();
            }
        }

        possibleActions.remove(executedAction);

        return possibleActions;
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
