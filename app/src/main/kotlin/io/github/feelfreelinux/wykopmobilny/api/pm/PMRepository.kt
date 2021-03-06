package io.github.feelfreelinux.wykopmobilny.api.pm

import io.github.feelfreelinux.wykopmobilny.api.errorhandler.ErrorHandlerTransformer
import io.github.feelfreelinux.wykopmobilny.api.entries.TypedInputStream
import io.github.feelfreelinux.wykopmobilny.api.errorhandler.ErrorHandler
import io.github.feelfreelinux.wykopmobilny.api.getRequestBody
import io.github.feelfreelinux.wykopmobilny.models.mapper.apiv2.ConversationMapper
import io.github.feelfreelinux.wykopmobilny.models.mapper.apiv2.FullConversationMapper
import io.github.feelfreelinux.wykopmobilny.models.mapper.apiv2.PMMessageMapper
import io.github.feelfreelinux.wykopmobilny.models.pojo.apiv2.models.ConversationDeleteResponse
import io.github.feelfreelinux.wykopmobilny.models.pojo.apiv2.models.ConversationResponse
import io.github.feelfreelinux.wykopmobilny.models.pojo.apiv2.models.PMMessageResponse
import io.github.feelfreelinux.wykopmobilny.models.pojo.apiv2.responses.FullConversationResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit

class PMRepository(val retrofit: Retrofit) : PMApi {
    private val pmretrofitApi by lazy { retrofit.create(PMRetrofitApi::class.java) }

    override fun getConversations() = pmretrofitApi.getConversations()
            .compose<List<ConversationResponse>>(ErrorHandlerTransformer())
            .map { it.map { ConversationMapper.map(it) } }

    override fun getConversation(user : String) = pmretrofitApi.getConversation(user)
            .flatMap<FullConversationResponse>(ErrorHandler())
            .map { FullConversationMapper.map(it) }

    override fun deleteConversation(user : String)
            = pmretrofitApi.deleteConversation(user)
            .compose<ConversationDeleteResponse>(ErrorHandlerTransformer())

    override fun sendMessage(body : String, user : String, embed: String?) =
            pmretrofitApi.sendMessage(body, user, embed)
                    .compose<PMMessageResponse>(ErrorHandlerTransformer())
                    .map { PMMessageMapper.map(it) }

    override fun sendMessage(body : String, user : String, embed: TypedInputStream) =
            pmretrofitApi.sendMessage(body.toRequestBody(),
                    user,
                    MultipartBody.Part.createFormData(
                            "embed",
                            embed.fileName,
                            embed.inputStream.getRequestBody(embed.mimeType))!!)
                    .compose<PMMessageResponse>(ErrorHandlerTransformer())
                    .map { PMMessageMapper.map(it) }

    private fun String.toRequestBody() = RequestBody.create(MultipartBody.FORM, this)!!
}