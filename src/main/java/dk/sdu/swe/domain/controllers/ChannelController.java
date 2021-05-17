package dk.sdu.swe.domain.controllers;

import dk.sdu.swe.data.dao.ChannelDAOImpl;
import dk.sdu.swe.domain.models.Channel;
import dk.sdu.swe.domain.persistence.IChannelDAO;

import java.util.List;

public class ChannelController {

    private IChannelDAO channelDAO;

    private static ChannelController instance;

    private ChannelController() {
        channelDAO = ChannelDAOImpl.getInstance();
    }

    public static ChannelController getInstance() {
        if (instance == null) {
            instance = new ChannelController();
        }
        return instance;
    }

    public List<Channel> getAll() {
        return channelDAO.getAll();
    }

}
