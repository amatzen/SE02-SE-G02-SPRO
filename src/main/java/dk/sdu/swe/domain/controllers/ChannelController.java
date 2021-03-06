package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.domain.controllers.contracts.IChannelController;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.persistence.IChannelDAO;
import dk.sdu.swe.persistence.dao.ChannelDAOImpl;

import java.util.List;
import java.util.Optional;

/**
 * The type Channel controller.
 */
public class ChannelController implements IChannelController {

    private static IChannelController instance;
    private final IChannelDAO channelDAO;

    private ChannelController() {
        channelDAO = ChannelDAOImpl.getInstance();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static IChannelController getInstance() {
        if (instance == null) {
            instance = new ChannelController();
        }
        return instance;
    }

    @Override
    public List<Channel> getAll() {
        return channelDAO.getAll();
    }

    public Optional<Channel> get(int id) {
        return channelDAO.getById(Long.parseLong(String.valueOf(id)));
    }

}
