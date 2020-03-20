package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserMusicItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserMusicItemRepository")
public interface UserMusicItemRepository extends JpaRepository<UserMusicItem, Long> {
    Optional<UserMusicItem> findByUserAndMusicId(UserData userData, int musicId);

    Page<UserMusicItem> findByUser_Card_ExtId(int userId, Pageable page);
}
