package icu.samnyan.aqua.sega.ongeki.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.ongeki.handler.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("ongeki")
public class OngekiController {

    private final GetGameEventHandler getGameEventHandler;
    private final GetGameIdlistHandler getGameIdlistHandler;
    private final GetGameMessageHandler getGameMessageHandler;
    private final GetGamePointHandler getGamePointHandler;
    private final GetGamePresentHandler getGamePresentHandler;
    private final GetGameRankingHandler getGameRankingHandler;
    private final GetGameRewardHandler getGameRewardHandler;
    private final GetGameSettingHandler getGameSettingHandler;
    private final GetUserActivityHandler getUserActivityHandler;
    private final GetUserBpBaseHandler getUserBpBaseHandler;
    private final GetUserCardHandler getUserCardHandler;
    private final GetUserChapterHandler getUserChapterHandler;
    private final GetUserCharacterHandler getUserCharacterHandler;
    private final GetUserDataHandler getUserDataHandler;
    private final GetUserDeckByKeyHandler getUserDeckByKeyHandler;
    private final GetUserEventPointHandler getUserEventPointHandler;
    private final GetUserEventRankingHandler getUserEventRankingHandler;
    private final GetUserItemHandler getUserItemHandler;
    private final GetUserLoginBonusHandler getUserLoginBonusHandler;
    private final GetUserMissionPointHandler getUserMissionPointHandler;
    private final GetUserMusicHandler getUserMusicHandler;
    private final GetUserMusicItemHandler getUserMusicItemHandler;
    private final GetUserOptionHandler getUserOptionHandler;
    private final GetUserPreviewHandler getUserPreviewHandler;
    private final GetUserRatinglogListHandler getUserRatinglogListHandler;
    private final GetUserRecentRatingHandler getUserRecentRatingHandler;
    private final GetUserRegionHandler getUserRegionHandler;
    private final GetUserStoryHandler getUserStoryHandler;
    private final GetUserTrainingRoomByKeyHandler getUserTrainingRoomByKeyHandler;
    private final UpsertUserAllHandler upsertUserAllHandler;

    @Autowired
    public OngekiController(GetGameEventHandler getGameEventHandler, GetGameIdlistHandler getGameIdlistHandler, GetGameMessageHandler getGameMessageHandler, GetGamePointHandler getGamePointHandler, GetGamePresentHandler getGamePresentHandler, GetGameRankingHandler getGameRankingHandler, GetGameRewardHandler getGameRewardHandler, GetGameSettingHandler getGameSettingHandler, GetUserActivityHandler getUserActivityHandler, GetUserBpBaseHandler getUserBpBaseHandler, GetUserCardHandler getUserCardHandler, GetUserChapterHandler getUserChapterHandler, GetUserCharacterHandler getUserCharacterHandler, GetUserDataHandler getUserDataHandler, GetUserDeckByKeyHandler getUserDeckByKeyHandler, GetUserEventPointHandler getUserEventPointHandler, GetUserEventRankingHandler getUserEventRankingHandler, GetUserItemHandler getUserItemHandler, GetUserLoginBonusHandler getUserLoginBonusHandler, GetUserMissionPointHandler getUserMissionPointHandler, GetUserMusicHandler getUserMusicHandler, GetUserMusicItemHandler getUserMusicItemHandler, GetUserOptionHandler getUserOptionHandler, GetUserPreviewHandler getUserPreviewHandler, GetUserRatinglogListHandler getUserRatinglogListHandler, GetUserRecentRatingHandler getUserRecentRatingHandler, GetUserRegionHandler getUserRegionHandler, GetUserStoryHandler getUserStoryHandler, GetUserTrainingRoomByKeyHandler getUserTrainingRoomByKeyHandler, UpsertUserAllHandler upsertUserAllHandler) {
        this.getGameEventHandler = getGameEventHandler;
        this.getGameIdlistHandler = getGameIdlistHandler;
        this.getGameMessageHandler = getGameMessageHandler;
        this.getGamePointHandler = getGamePointHandler;
        this.getGamePresentHandler = getGamePresentHandler;
        this.getGameRankingHandler = getGameRankingHandler;
        this.getGameRewardHandler = getGameRewardHandler;
        this.getGameSettingHandler = getGameSettingHandler;
        this.getUserActivityHandler = getUserActivityHandler;
        this.getUserBpBaseHandler = getUserBpBaseHandler;
        this.getUserCardHandler = getUserCardHandler;
        this.getUserChapterHandler = getUserChapterHandler;
        this.getUserCharacterHandler = getUserCharacterHandler;
        this.getUserDataHandler = getUserDataHandler;
        this.getUserDeckByKeyHandler = getUserDeckByKeyHandler;
        this.getUserEventPointHandler = getUserEventPointHandler;
        this.getUserEventRankingHandler = getUserEventRankingHandler;
        this.getUserItemHandler = getUserItemHandler;
        this.getUserLoginBonusHandler = getUserLoginBonusHandler;
        this.getUserMissionPointHandler = getUserMissionPointHandler;
        this.getUserMusicHandler = getUserMusicHandler;
        this.getUserMusicItemHandler = getUserMusicItemHandler;
        this.getUserOptionHandler = getUserOptionHandler;
        this.getUserPreviewHandler = getUserPreviewHandler;
        this.getUserRatinglogListHandler = getUserRatinglogListHandler;
        this.getUserRecentRatingHandler = getUserRecentRatingHandler;
        this.getUserRegionHandler = getUserRegionHandler;
        this.getUserStoryHandler = getUserStoryHandler;
        this.getUserTrainingRoomByKeyHandler = getUserTrainingRoomByKeyHandler;
        this.upsertUserAllHandler = upsertUserAllHandler;
    }

    @PostMapping("ExtendLockTimeApi")
    String extendLockTime(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return "{\"returnCode\":1,\"apiName\":\"extendLockTime\"}";
    }

    @PostMapping("GameLoginApi")
    String gameLogin(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return "{\"returnCode\":1,\"apiName\":\"gameLogin\"}";
    }

    @PostMapping("GameLogoutApi")
    String gameLogout(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return "{\"returnCode\":1,\"apiName\": \"gameLogout\"}";
    }

    @PostMapping("GetGameEventApi")
    String getGameEvent(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameEventHandler.handle(request);
    }

    @PostMapping("GetGameIdlistApi")
    String getGameIdList(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameIdlistHandler.handle(request);
    }

    @PostMapping("GetGameMessageApi")
    String getGameMessage(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameMessageHandler.handle(request);
    }

    @PostMapping("GetGamePointApi")
    String getGamePoint(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGamePointHandler.handle(request);
    }

    @PostMapping("GetGamePresentApi")
    String getGamePresent(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGamePresentHandler.handle(request);
    }

    @PostMapping("GetGameRankingApi")
    String getGameRanking(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameRankingHandler.handle(request);
    }

    @PostMapping("GetGameRewardApi")
    String getGameReward(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameRewardHandler.handle(request);
    }

    @PostMapping("GetGameSettingApi")
    String getGameSetting(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getGameSettingHandler.handle(request);
    }

    @PostMapping("GetUserActivityApi")
    String getUserActivity(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserActivityHandler.handle(request);
    }

    @PostMapping("GetUserBpBaseApi")
    String getUserBpBase(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserBpBaseHandler.handle(request);
    }

    @PostMapping("GetUserCardApi")
    String getUserCard(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCardHandler.handle(request);
    }
    @PostMapping("GetUserChapterApi")
    String getUserChapter(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserChapterHandler.handle(request);
    }

    @PostMapping("GetUserCharacterApi")
    String getUserCharacter(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserCharacterHandler.handle(request);
    }

    @PostMapping("GetUserDataApi")
    String getUserData(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserDataHandler.handle(request);
    }

    @PostMapping("GetUserDeckByKeyApi")
    String getUserDeckByKey(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserDeckByKeyHandler.handle(request);
    }

    @PostMapping("GetUserEventPointApi")
    String getUserEventPoint(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserEventPointHandler.handle(request);
    }

    @PostMapping("GetUserEventRankingApi")
    String getUserEventRanking(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserEventRankingHandler.handle(request);
    }

    @PostMapping("GetUserItemApi")
    String getUserItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserItemHandler.handle(request);
    }

    @PostMapping("GetUserLoginBonusApi")
    String getUserLoginBonus(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserLoginBonusHandler.handle(request);
    }

    @PostMapping("GetUserMissionPointApi")
    String getUserMissionPoint(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMissionPointHandler.handle(request);
    }

    @PostMapping("GetUserMusicApi")
    String getUserMusic(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMusicHandler.handle(request);
    }

    @PostMapping("GetUserMusicItemApi")
    String getUserMusicItem(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserMusicItemHandler.handle(request);
    }

    @PostMapping("GetUserOptionApi")
    String getUserOption(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserOptionHandler.handle(request);
    }

    @PostMapping("GetUserPreviewApi")
    String getUserPreview(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserPreviewHandler.handle(request);
    }

    @PostMapping("GetUserRatinglogApi")
    String getUserRatinglog(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRatinglogListHandler.handle(request);
    }

    @PostMapping("GetUserRecentRatingApi")
    String getUserRecentRating(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRecentRatingHandler.handle(request);
    }

    @PostMapping("GetUserRegionApi")
    String getUserRegion(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserRegionHandler.handle(request);
    }

    @PostMapping("GetUserStoryApi")
    String getUserStory(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserStoryHandler.handle(request);
    }

    @PostMapping("GetUserTrainingRoomByKeyApi")
    String getUserTrainingRoomByKey(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return getUserTrainingRoomByKeyHandler.handle(request);
    }

    @PostMapping("UpsertClientBookkeepingApi")
    String upsertClientBookkeeping(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1},\"apiName\":\"upsertClientBookkeeping\"";
    }

    @PostMapping("UpsertClientDevelopApi")
    String upsertClientDevelop(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1},\"apiName\":\"upsertClientDevelop\"";
    }

    @PostMapping("UpsertClientErrorApi")
    String upsertClientError(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"upsertClientError\"}";
    }

    @PostMapping("UpsertClientSettingApi")
    String upsertClientSetting(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"upsertClientSetting\"}";
    }

    @PostMapping("UpsertClientTestmodeApi")
    String upsertClientTestmode(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"upsertClientTestmode\"}";
    }

    @PostMapping("UpsertUserGplogApi")
    String upsertUserGplog(@ModelAttribute Map<String, Object> request) {
        return "{\"returnCode\":1,\"apiName\":\"upsertUserGplog\"}";
    }

    @PostMapping("UpsertUserAllApi")
    String upsertUserAll(@ModelAttribute Map<String, Object> request) throws JsonProcessingException {
        return upsertUserAllHandler.handle(request);
    }

}
