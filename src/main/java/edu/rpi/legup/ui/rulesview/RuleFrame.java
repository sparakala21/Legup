package edu.rpi.legup.ui.rulesview;

import edu.rpi.legup.controller.RuleController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import edu.rpi.legup.model.Puzzle;
import edu.rpi.legup.model.gameboard.Board;
import edu.rpi.legup.model.rules.BasicRule;
import edu.rpi.legup.model.rules.CaseRule;
import edu.rpi.legup.model.rules.ContradictionRule;
import edu.rpi.legup.model.rules.Rule;

import java.util.HashMap;
import java.util.Map;

public class RuleFrame
{
    @FXML
    private TabPane rules_pane;

    @FXML
    private Tab basic_rules_tab;

    @FXML
    private ScrollPane basic_rules_scroll_pane;

    @FXML
    private Group basic_rules_button_group;

    @FXML
    private Tab contradiction_rules_tab;

    @FXML
    private ScrollPane contradiction_rules_scroll_pane;

    @FXML
    private Group contradiction_rules_button_group;

    @FXML
    private Tab case_rules_tab;

    @FXML
    private ScrollPane case_rules_scroll_pane;

    @FXML
    private Group case_rules_button_group;

    private Map<Button, Rule> rulesMap;

    private RuleController controller;

    public RuleFrame(RuleController controller)
    {
        this.controller = controller;
        this.rulesMap = new HashMap<>();
    }

    /**
     * Reset the rules button and status string
     */
    public void resetRuleButtons()
    {
        resetStatus();
    }

    /**
     * Reset the status label to the empty string
     */
    public void resetStatus()
    {
        //((GridUI)GameBoardFacade.getInstance().getLegupUI()).getTreePanel().updateStatus();
    }



    /**
     * Sets all the rules for the rule frame
     *
     * @param puzzle edu.rpi.legup.puzzle game
     */
    public void setRules(Puzzle puzzle)
    {
        rulesMap.clear();
        for(BasicRule rule : puzzle.getBasicRules())
        {
            Button newRuleButton = new Button();
            newRuleButton.setText(rule.getRuleName());
            newRuleButton.setTooltip(new Tooltip(rule.getDescription()));
            newRuleButton.setGraphic(new ImageView(rule.getImage()));

            rulesMap.put(newRuleButton, rule);
        }

        for(CaseRule rule : puzzle.getCaseRules())
        {
            Button newRuleButton = new Button();
            newRuleButton.setText(rule.getRuleName());
            newRuleButton.setTooltip(new Tooltip(rule.getDescription()));
            newRuleButton.setGraphic(new ImageView(rule.getImage()));

            rulesMap.put(newRuleButton, rule);
        }

        for(ContradictionRule rule : puzzle.getContradictionRules())
        {
            Button newRuleButton = new Button();
            newRuleButton.setText(rule.getRuleName());
            newRuleButton.setTooltip(new Tooltip(rule.getDescription()));
            newRuleButton.setGraphic(new ImageView(rule.getImage()));

            rulesMap.put(newRuleButton, rule);
        }
    }

    /**
     * Board element has changed
     *
     * @param board board state
     */
    public void boardDataChanged(Board board)
    {
        this.resetStatus();
    }

    /**
     * Gets the RuleController for this RuleFrame
     *
     * @return rule edu.rpi.legup.controller
     */
    public RuleController getController()
    {
        return controller;
    }

    public TabPane getTabbedPane()
    {
        return rules_pane;
    }

    public Tab getBasic_rules_tab()
    {
        return basic_rules_tab;
    }

    public Tab getCase_rules_tab()
    {
        return case_rules_tab;
    }

    public Tab getContradiction_rules_tab()
    {
        return contradiction_rules_tab;
    }
}
