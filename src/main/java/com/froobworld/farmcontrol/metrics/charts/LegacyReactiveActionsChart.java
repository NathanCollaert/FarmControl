package com.froobworld.farmcontrol.metrics.charts;

import com.froobworld.farmcontrol.FarmControl;
import com.froobworld.farmcontrol.controller.ActionProfile;
import com.froobworld.farmcontrol.controller.action.Action;
import org.bstats.charts.AdvancedPie;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;

public class LegacyReactiveActionsChart extends AdvancedPie {
    public LegacyReactiveActionsChart(FarmControl farmControl) {
        super("reactive_actions_in_use", () -> {
            Map<String, Integer> actionCountMap = new HashMap<>();
            for (World world : Bukkit.getWorlds()) {
                for (String profileName : farmControl.getFcConfig().worldSettings.of(world).profiles.reactive.get()) {
                    ActionProfile actionProfile = farmControl.getProfileManager().getActionProfile(profileName);
                    if (actionProfile != null) {
                        for (Action action : actionProfile.getActions()) {
                            actionCountMap.put(action.getName(), 1);
                        }
                    }
                }
            }
            return actionCountMap;
        });
    }
}
