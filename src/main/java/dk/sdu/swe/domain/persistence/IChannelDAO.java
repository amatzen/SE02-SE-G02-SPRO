package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Channel;

import java.util.Optional;

public interface IChannelDAO extends IDAO<Channel> {

    public Optional<Channel> getByEpgId(int epgId);

}
