package command;

import dictionary.GameEntities;
import entity.*;

import java.util.Set;

public class ChangeLocation extends Command {
    private IInteractable parent;
    private Set<String> args;

    public ChangeLocation(final Set<String> args, final IInteractable parent) {
        this.args = args;
        this.parent = parent;
    }

    @Override
    public String apply(final String newLocation, final GameEntities ge) {
        Area area = ge.getAreaOrElse(parent.getCurrentLocation());

        if (!area.connectsTo(newLocation)) { return "You cannot go there from here."; }

        for (final String o : area.getObstacles())
        {
            final Obstacle obstacle = ge.getObstacleOrElse(o);
            final String nextArea   = area.getConnection(newLocation);
            if (obstacle.getState() && obstacle.getBlocks().equalsIgnoreCase(nextArea))
            {
                return String.format("There is a %s in the way.", obstacle.getName());
            }
        }

        final String newLocationName = area.getConnection(newLocation);
        parent.setCurrentLocation(newLocationName);

        return ge.findEntityOrElse(newLocationName).getDescription();
    }

    public String apply(final Area object, final GameEntities ge) {
        return "";
    }

    public String apply(final Item object, final GameEntities ge) {
        return "";
    }

    public String apply(final Npc object, final GameEntities ge) {
        return "";
    }

    public String apply(final Obstacle object, final GameEntities ge) {
        return "";
    }

    public String apply(final Player object, final GameEntities ge) {
        return "";
    }
}
