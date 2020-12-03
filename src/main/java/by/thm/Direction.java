package by.thm;

import org.bukkit.block.BlockFace;

public class Direction {

    public static BlockFace yawToFace(float yaw, boolean useSubCardinalDirections) {
        if (useSubCardinalDirections)
            return radial[Math.round(yaw / 45f) & 0x7].getOppositeFace();

        return axis[Math.round(yaw / 90f) & 0x3].getOppositeFace();
    }

    public static String toText(BlockFace blockFace) {
        switch (blockFace) {
            case NORTH:
                return "N";
            case NORTH_EAST:
                return "NE";
            case NORTH_WEST:
                return "NW";
            case SOUTH:
                return "S";
            case SOUTH_EAST:
                return "SE";
            case SOUTH_WEST:
                return "SW";
            case EAST:
                return "E";
            case WEST:
                return "W";
            default:
                return null;
        }
    }

    private static final BlockFace[] axis = { BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };
    private static final BlockFace[] radial = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };

}
