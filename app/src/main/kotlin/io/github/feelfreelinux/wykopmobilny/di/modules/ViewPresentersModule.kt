package io.github.feelfreelinux.wykopmobilny.di.modules

import dagger.Module
import dagger.Provides
import io.github.feelfreelinux.wykopmobilny.api.entries.EntriesApi
import io.github.feelfreelinux.wykopmobilny.api.notifications.NotificationsApi
import io.github.feelfreelinux.wykopmobilny.ui.dialogs.votersdialog.VotersDialogPresenter
import io.github.feelfreelinux.wykopmobilny.ui.widgets.buttons.favorite.entry.EntryFavoriteButtonPresenter
import io.github.feelfreelinux.wykopmobilny.ui.widgets.buttons.vote.entry.EntryVoteButtonPresenter
import io.github.feelfreelinux.wykopmobilny.ui.widgets.buttons.vote.entry.comment.EntryCommentVoteButtonPresenter
import io.github.feelfreelinux.wykopmobilny.ui.widgets.drawerheaderview.DrawerHeaderPresenter
import io.github.feelfreelinux.wykopmobilny.ui.widgets.entry.EntryPresenter
import io.github.feelfreelinux.wykopmobilny.ui.widgets.entry.comment.CommentPresenter
import io.github.feelfreelinux.wykopmobilny.ui.widgets.survey.SurveyPresenter
import io.github.feelfreelinux.wykopmobilny.utils.rx.SubscriptionHelperApi

@Module
class ViewPresentersModule {
    @Provides
    fun providesEntryVoteButtonPresenter(subscriptionHelperApi: SubscriptionHelperApi, entriesApi: EntriesApi) = EntryVoteButtonPresenter(subscriptionHelperApi, entriesApi)

    @Provides
    fun providesEntryCommentVoteButtonPresenter(subscriptionHelperApi: SubscriptionHelperApi, entriesApi: EntriesApi) = EntryCommentVoteButtonPresenter(subscriptionHelperApi, entriesApi)

    @Provides
    fun providesEntryFavoriteButtonPresenter(subscriptionHelperApi: SubscriptionHelperApi, entriesApi: EntriesApi) = EntryFavoriteButtonPresenter(subscriptionHelperApi, entriesApi)

    @Provides
    fun providesEntryPresenter(subscriptionHelperApi: SubscriptionHelperApi, entriesApi: EntriesApi) = EntryPresenter(subscriptionHelperApi, entriesApi)

    @Provides
    fun providesDrawerHeaderPresenter(subscriptionHelperApi: SubscriptionHelperApi, notificationsApi: NotificationsApi) = DrawerHeaderPresenter(subscriptionHelperApi, notificationsApi)

    @Provides
    fun providesCommentPresenter(subscriptionHelperApi: SubscriptionHelperApi, entriesApi: EntriesApi) = CommentPresenter(subscriptionHelperApi, entriesApi)

    @Provides
    fun providesSurveyPresenter(subscriptionHelperApi: SubscriptionHelperApi, entriesApi: EntriesApi) = SurveyPresenter(subscriptionHelperApi, entriesApi)

    @Provides
    fun providesVotersDialogPresenter(subscriptionHelperApi: SubscriptionHelperApi, entriesApi: EntriesApi) = VotersDialogPresenter(subscriptionHelperApi, entriesApi)
}