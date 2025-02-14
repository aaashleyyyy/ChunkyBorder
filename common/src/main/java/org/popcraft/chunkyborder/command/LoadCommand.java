package org.popcraft.chunkyborder.command;

import org.popcraft.chunky.Chunky;
import org.popcraft.chunky.Selection;
import org.popcraft.chunky.command.ChunkyCommand;
import org.popcraft.chunky.command.CommandArguments;
import org.popcraft.chunky.platform.Sender;
import org.popcraft.chunky.platform.World;
import org.popcraft.chunky.util.TranslationKey;
import org.popcraft.chunkyborder.BorderData;
import org.popcraft.chunkyborder.ChunkyBorder;

import java.util.List;
import java.util.Map;

public class LoadCommand implements ChunkyCommand {
    private final ChunkyBorder chunkyBorder;

    public LoadCommand(final ChunkyBorder chunkyBorder) {
        this.chunkyBorder = chunkyBorder;
    }

    @Override
    public void execute(final Sender sender, final CommandArguments arguments) {
        final Chunky chunky = chunkyBorder.getChunky();
        final Map<String, BorderData> borders = chunkyBorder.getBorders();
        final Selection selection = chunky.getSelection().build();
        final World world = selection.world();
        final BorderData currentBorder = borders.get(world.getName());
        if (currentBorder != null) {
            final Selection.Builder newSelection = chunky.getSelection();
            newSelection.world(world);
            newSelection.shape(currentBorder.getShape());
            newSelection.center(currentBorder.getCenterX(), currentBorder.getCenterZ());
            newSelection.radiusX(currentBorder.getRadiusX());
            newSelection.radiusZ(currentBorder.getRadiusZ());
            sender.sendMessagePrefixed(TranslationKey.FORMAT_BORDER_LOAD, world.getName());
        } else {
            sender.sendMessagePrefixed(TranslationKey.FORMAT_BORDER_NO_BORDER, world.getName());
        }
    }

    @Override
    public List<String> suggestions(final CommandArguments commandArguments) {
        return List.of();
    }
}
