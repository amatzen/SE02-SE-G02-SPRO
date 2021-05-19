package dk.sdu.swe.domain.controllers.contracts;

import dk.sdu.swe.domain.models.Channel;

import java.util.List;

public interface IChannelController {
    List<Channel> getAll();
}
