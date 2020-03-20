package icu.samnyan.aqua.sega.ongeki.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.general.service.CardService;
import icu.samnyan.aqua.sega.ongeki.dao.userdata.*;
import icu.samnyan.aqua.sega.ongeki.handler.BaseHandler;
import icu.samnyan.aqua.sega.ongeki.model.response.CodeResp;
import icu.samnyan.aqua.sega.ongeki.model.userdata.*;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The handler for loading basic profile information.
 * <p>
 * return null if no profile exist
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("OngekiUserAllHandler")
public class UpsertUserAllHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(UpsertUserAllHandler.class);

    private final BasicMapper mapper;

    private final CardService cardService;

    private final UserDataRepository userDataRepository;
    private final UserOptionRepository userOptionRepository;
    private final UserPlaylogRepository userPlaylogRepository;
    private final UserActivityRepository userActivityRepository;
    private final UserMusicDetailRepository userMusicDetailRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final UserCardRepository userCardRepository;
    private final UserDeckRepository userDeckRepository;
    private final UserStoryRepository userStoryRepository;
    private final UserChapterRepository userChapterRepository;
    private final UserItemRepository userItemRepository;
    private final UserMusicItemRepository userMusicItemRepository;
    private final UserLoginBonusRepository userLoginBonusRepository;
    private final UserEventPointRepository userEventPointRepository;

    @Autowired
    public UpsertUserAllHandler(BasicMapper mapper,
                                CardService cardService, UserDataRepository userDataRepository, UserOptionRepository userOptionRepository, UserPlaylogRepository userPlaylogRepository, UserActivityRepository userActivityRepository, UserMusicDetailRepository userMusicDetailRepository, UserCharacterRepository userCharacterRepository, UserCardRepository userCardRepository, UserDeckRepository userDeckRepository, UserStoryRepository userStoryRepository, UserChapterRepository userChapterRepository, UserItemRepository userItemRepository, UserMusicItemRepository userMusicItemRepository, UserLoginBonusRepository userLoginBonusRepository, UserEventPointRepository userEventPointRepository) {
        this.mapper = mapper;
        this.cardService = cardService;
        this.userDataRepository = userDataRepository;
        this.userOptionRepository = userOptionRepository;
        this.userPlaylogRepository = userPlaylogRepository;
        this.userActivityRepository = userActivityRepository;
        this.userMusicDetailRepository = userMusicDetailRepository;
        this.userCharacterRepository = userCharacterRepository;
        this.userCardRepository = userCardRepository;
        this.userDeckRepository = userDeckRepository;
        this.userStoryRepository = userStoryRepository;
        this.userChapterRepository = userChapterRepository;
        this.userItemRepository = userItemRepository;
        this.userMusicItemRepository = userMusicItemRepository;
        this.userLoginBonusRepository = userLoginBonusRepository;
        this.userEventPointRepository = userEventPointRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        Integer userId = (Integer) request.get("userId");
        Map<String, Object> upsertUserAll = (Map<String, Object>) request.get("upsertUserAll");

        // UserData
        UserData userData;
        UserData newUserData;
        if (!upsertUserAll.containsKey("userData")) {
            return null;
        } else {
            Map<String, Object> userDataMap = ((List<Map<String, Object>>) upsertUserAll.get("userData")).get(0);

            Optional<UserData> userOptional = userDataRepository.findByCard_ExtId(userId);

            if(userOptional.isPresent()) {
                userData = userOptional.get();
            } else {
                userData = new UserData();
                Card card = cardService.getCardByExtId(userId).orElseThrow();
                userData.setCard(card);
            }
            newUserData = mapper.convert(userDataMap, UserData.class);

            newUserData.setId(userData.getId());
            newUserData.setCard(userData.getCard());

            userDataRepository.save(newUserData);
        }

        // UserOption
        if (upsertUserAll.containsKey("userOption")) {
            Map<String, Object> userOptionMap = ((List<Map<String, Object>>) upsertUserAll.get("userOption")).get(0);

            Optional<UserOption> userOptionOptional = userOptionRepository.findByUser(newUserData);
            UserOption userOption = userOptionOptional.orElseGet(() -> new UserOption(newUserData));

            UserOption newUserOption = mapper.convert(userOptionMap, UserOption.class);
            newUserOption.setId(userOption.getId());
            newUserOption.setUser(userOption.getUser());

            userOptionRepository.save(newUserOption);
        }

        // UserPlaylogList
        if (upsertUserAll.containsKey("userPlaylogList")) {
            List<Map<String, Object>> userPlaylogList = ((List<Map<String, Object>>) upsertUserAll.get("userPlaylogList"));
            List<UserPlaylog> newUserPlaylogList = new ArrayList<>();

            for (Map<String, Object> userPlayLogMap : userPlaylogList) {
                UserPlaylog newUserPlaylog = mapper.convert(userPlayLogMap, UserPlaylog.class);
                newUserPlaylog.setUser(newUserData);
                newUserPlaylogList.add(newUserPlaylog);
            }

            userPlaylogRepository.saveAll(newUserPlaylogList);
        }

        // UserSessionlogList doesn't need to save for a private server

        // UserActivityList
        if (upsertUserAll.containsKey("userActivityList")) {
            List<Map<String, Object>> userActivityList = ((List<Map<String, Object>>) upsertUserAll.get("userActivityList"));
            List<UserActivity> newUserActivityList = new ArrayList<>();

            for (Map<String, Object> userActivityMap : userActivityList) {
                Integer kind = (Integer) userActivityMap.get("kind");
                Integer id = (Integer) userActivityMap.get("id");

                if(kind != 0 && id!=0) {
                    Optional<UserActivity> activityOptional = userActivityRepository.findByUserAndKindAndActivityId(newUserData, kind, id);
                    UserActivity userActivity = activityOptional.orElseGet(() -> new UserActivity(newUserData));

                    UserActivity newUserActivity = mapper.convert(userActivityMap, UserActivity.class);
                    newUserActivity.setId(userActivity.getId());
                    newUserActivity.setUser(newUserData);
                    newUserActivityList.add(newUserActivity);
                }
            }
            userActivityRepository.saveAll(newUserActivityList);
        }

        // UserRecentRatingList
        // This doesn't need to save. It can get from playlog

        // UserBpBaseList
        // From UserBatterPoint.cs, the game doesn't use the value here. So it doesn't need to save.

        // userRatingBaseBestNewList
        // userRatingBaseBestList
        // userRatingBaseHotList
        // userRatingBaseNextNewList
        // userRatingBaseNextList
        // userRatingBaseHotNextList

        // UserMusicDetailList
        if (upsertUserAll.containsKey("userMusicDetailList")) {
            List<Map<String, Object>> userMusicDetailList = ((List<Map<String, Object>>) upsertUserAll.get("userMusicDetailList"));
            List<UserMusicDetail> newUserMusicDetailList = new ArrayList<>();

            for (Map<String, Object> userMusicDetailMap : userMusicDetailList) {
                Integer musicId = (Integer) userMusicDetailMap.get("musicId");
                Integer level = (Integer) userMusicDetailMap.get("level");

                Optional<UserMusicDetail> musicDetailOptional = userMusicDetailRepository.findByUserAndMusicIdAndLevel(newUserData, musicId, level);
                UserMusicDetail userMusicDetail = musicDetailOptional.orElseGet(() -> new UserMusicDetail(newUserData));

                UserMusicDetail newUserMusicDetail = mapper.convert(userMusicDetailMap, UserMusicDetail.class);
                newUserMusicDetail.setId(userMusicDetail.getId());
                newUserMusicDetail.setUser(newUserData);
                newUserMusicDetailList.add(newUserMusicDetail);
            }
            userMusicDetailRepository.saveAll(newUserMusicDetailList);
        }

        // UserCharacterList
        if (upsertUserAll.containsKey("userCharacterList")) {
            List<Map<String, Object>> userCharacterList = ((List<Map<String, Object>>) upsertUserAll.get("userCharacterList"));
            List<UserCharacter> newUserCharacterList = new ArrayList<>();

            for (Map<String, Object> userCharacterMap : userCharacterList) {
                Integer characterId = (Integer) userCharacterMap.get("characterId");

                Optional<UserCharacter> characterOptional = userCharacterRepository.findByUserAndCharacterId(newUserData, characterId);
                UserCharacter userCharacter = characterOptional.orElseGet(() -> new UserCharacter(newUserData));

                UserCharacter newUserCharacter = mapper.convert(userCharacterMap, UserCharacter.class);
                newUserCharacter.setId(userCharacter.getId());
                newUserCharacter.setUser(newUserData);
                newUserCharacterList.add(newUserCharacter);
            }
            userCharacterRepository.saveAll(newUserCharacterList);
        }

        // UserCardList
        if (upsertUserAll.containsKey("userCardList")) {
            List<Map<String, Object>> userCardList = ((List<Map<String, Object>>) upsertUserAll.get("userCardList"));
            List<UserCard> newUserCardList = new ArrayList<>();

            for (Map<String, Object> userCardMap : userCardList) {
                Integer cardId = (Integer) userCardMap.get("cardId");

                Optional<UserCard> cardOptional = userCardRepository.findByUserAndCardId(newUserData, cardId);
                UserCard userCard = cardOptional.orElseGet(() -> new UserCard(newUserData));

                UserCard newUserCard = mapper.convert(userCardMap, UserCard.class);
                newUserCard.setId(userCard.getId());
                newUserCard.setUser(newUserData);
                newUserCardList.add(newUserCard);
            }
            userCardRepository.saveAll(newUserCardList);
        }

        // UserDeckList
        if (upsertUserAll.containsKey("userDeckList")) {
            List<Map<String, Object>> userDeckList = ((List<Map<String, Object>>) upsertUserAll.get("userDeckList"));
            List<UserDeck> newUserDeckList = new ArrayList<>();

            for (Map<String, Object> userDeckMap : userDeckList) {
                Integer deckId = (Integer) userDeckMap.get("deckId");

                Optional<UserDeck> deckOptional = userDeckRepository.findByUserAndDeckId(newUserData, deckId);
                UserDeck userDeck = deckOptional.orElseGet(() -> new UserDeck(newUserData));

                UserDeck newUserDeck = mapper.convert(userDeckMap, UserDeck.class);
                newUserDeck.setId(userDeck.getId());
                newUserDeck.setUser(newUserData);
                newUserDeckList.add(newUserDeck);
            }
            userDeckRepository.saveAll(newUserDeckList);
        }

        // TODO: userTrainingRoomList

        // UserStoryList
        if (upsertUserAll.containsKey("userStoryList")) {
            List<Map<String, Object>> userStoryList = ((List<Map<String, Object>>) upsertUserAll.get("userStoryList"));
            List<UserStory> newUserStoryList = new ArrayList<>();

            for (Map<String, Object> userStoryMap : userStoryList) {
                Integer storyId = (Integer) userStoryMap.get("storyId");

                Optional<UserStory> storyOptional = userStoryRepository.findByUserAndStoryId(newUserData, storyId);
                UserStory userStory = storyOptional.orElseGet(() -> new UserStory(newUserData));

                UserStory newUserStory = mapper.convert(userStoryMap, UserStory.class);
                newUserStory.setId(userStory.getId());
                newUserStory.setUser(newUserData);
                newUserStoryList.add(newUserStory);
            }
            userStoryRepository.saveAll(newUserStoryList);
        }


        // UserChapterList
        if (upsertUserAll.containsKey("userChapterList")) {
            List<Map<String, Object>> userChapterList = ((List<Map<String, Object>>) upsertUserAll.get("userChapterList"));
            List<UserChapter> newUserChapterList = new ArrayList<>();

            for (Map<String, Object> userChapterMap : userChapterList) {
                Integer chapterId = (Integer) userChapterMap.get("chapterId");

                Optional<UserChapter> chapterOptional = userChapterRepository.findByUserAndChapterId(newUserData, chapterId);
                UserChapter userChapter = chapterOptional.orElseGet(() -> new UserChapter(newUserData));

                UserChapter newUserChapter = mapper.convert(userChapterMap, UserChapter.class);
                newUserChapter.setId(userChapter.getId());
                newUserChapter.setUser(newUserData);
                newUserChapterList.add(newUserChapter);
            }
            userChapterRepository.saveAll(newUserChapterList);
        }


        // UserItemList
        if (upsertUserAll.containsKey("userItemList")) {
            List<Map<String, Object>> userItemList = ((List<Map<String, Object>>) upsertUserAll.get("userItemList"));
            List<UserItem> newUserItemList = new ArrayList<>();

            for (Map<String, Object> userItemMap : userItemList) {
                Integer itemKind = (Integer) userItemMap.get("itemKind");
                Integer itemId = (Integer) userItemMap.get("itemId");

                Optional<UserItem> itemOptional = userItemRepository.findByUserAndItemKindAndItemId(newUserData, itemKind, itemId);
                UserItem userItem = itemOptional.orElseGet(() -> new UserItem(newUserData));

                UserItem newUserItem = mapper.convert(userItemMap, UserItem.class);
                newUserItem.setId(userItem.getId());
                newUserItem.setUser(newUserData);
                newUserItemList.add(newUserItem);
            }
            userItemRepository.saveAll(newUserItemList);
        }

        // UserMusicItemList
        if (upsertUserAll.containsKey("userMusicItemList")) {
            List<Map<String, Object>> userMusicItemList = ((List<Map<String, Object>>) upsertUserAll.get("userMusicItemList"));
            List<UserMusicItem> newUserMusicItemList = new ArrayList<>();

            for (Map<String, Object> userMusicItemMap : userMusicItemList) {
                Integer musicId = (Integer) userMusicItemMap.get("musicId");

                Optional<UserMusicItem> musicItemOptional = userMusicItemRepository.findByUserAndMusicId(newUserData, musicId);
                UserMusicItem userMusicItem = musicItemOptional.orElseGet(() -> new UserMusicItem(newUserData));

                UserMusicItem newUserMusicItem = mapper.convert(userMusicItemMap, UserMusicItem.class);
                newUserMusicItem.setId(userMusicItem.getId());
                newUserMusicItem.setUser(newUserData);
                newUserMusicItemList.add(newUserMusicItem);
            }
            userMusicItemRepository.saveAll(newUserMusicItemList);
        }


        // userLoginBonusList
        if (upsertUserAll.containsKey("userLoginBonusList")) {
            List<Map<String, Object>> userLoginBonusList = ((List<Map<String, Object>>) upsertUserAll.get("userLoginBonusList"));
            List<UserLoginBonus> newUserLoginBonusList = new ArrayList<>();

            for (Map<String, Object> userLoginBonusMap : userLoginBonusList) {
                Integer bonusId = (Integer) userLoginBonusMap.get("bonusId");

                Optional<UserLoginBonus> loginBonusOptional = userLoginBonusRepository.findByUserAndBonusId(newUserData, bonusId);
                UserLoginBonus userLoginBonus = loginBonusOptional.orElseGet(() -> new UserLoginBonus(newUserData));

                UserLoginBonus newUserLoginBonus = mapper.convert(userLoginBonusMap, UserLoginBonus.class);
                newUserLoginBonus.setId(userLoginBonus.getId());
                newUserLoginBonus.setUser(newUserData);
                newUserLoginBonusList.add(newUserLoginBonus);
            }
            userLoginBonusRepository.saveAll(newUserLoginBonusList);
        }

        // UserEventPointList
        if (upsertUserAll.containsKey("userEventPointList")) {
            List<Map<String, Object>> userEventPointList = ((List<Map<String, Object>>) upsertUserAll.get("userEventPointList"));
            List<UserEventPoint> newUserEventPointList = new ArrayList<>();

            for (Map<String, Object> userEventPointMap : userEventPointList) {
                Integer eventId = (Integer) userEventPointMap.get("eventId");

                Optional<UserEventPoint> eventPointOptional = userEventPointRepository.findByUserAndEventId(newUserData, eventId);
                UserEventPoint userEventPoint = eventPointOptional.orElseGet(() -> new UserEventPoint(newUserData));

                UserEventPoint newUserEventPoint = mapper.convert(userEventPointMap, UserEventPoint.class);
                newUserEventPoint.setId(userEventPoint.getId());
                newUserEventPoint.setUser(newUserData);
                newUserEventPointList.add(newUserEventPoint);
            }
            userEventPointRepository.saveAll(newUserEventPointList);
        }

        // UserMissionPointList

        // UserRatinglogList


        String json = mapper.write(new CodeResp(1,"upsertUserAll"));
        logger.info("Response: " + json);
        return json;

    }
}
