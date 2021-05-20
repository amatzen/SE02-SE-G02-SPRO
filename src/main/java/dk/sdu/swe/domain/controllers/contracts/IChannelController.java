package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Channel;

import java.util.List;

/**
 * The interface Channel controller.
 */
public interface IChannelController {
    /**
     * Gets all.
     *
     * @return the all
     */
    List<Channel> getAll();
}
