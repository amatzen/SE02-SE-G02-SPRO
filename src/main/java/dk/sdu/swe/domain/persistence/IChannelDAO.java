package dk.sdu.swe.domain.persistence;

import dk.sdu.swe.domain.models.Channel;

import java.util.Optional;

/**
 * The interface Channel dao.
 */
public interface IChannelDAO extends IDAO<Channel> {

    /**
     * Gets by epg id.
     *
     * @param epgId the epg id
     * @return the by epg id
     */
    public Optional<Channel> getByEpgId(Long epgId);

}
