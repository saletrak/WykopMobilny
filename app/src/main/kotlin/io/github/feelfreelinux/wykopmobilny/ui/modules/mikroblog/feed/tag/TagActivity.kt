package io.github.feelfreelinux.wykopmobilny.ui.modules.mikroblog.feed.tag

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import io.github.feelfreelinux.wykopmobilny.R
import io.github.feelfreelinux.wykopmobilny.WykopApp
import io.github.feelfreelinux.wykopmobilny.base.BaseActivity
import io.github.feelfreelinux.wykopmobilny.models.dataclass.Entry
import io.github.feelfreelinux.wykopmobilny.models.fragments.DataFragment
import io.github.feelfreelinux.wykopmobilny.models.fragments.PagedDataModel
import io.github.feelfreelinux.wykopmobilny.models.fragments.getDataFragmentInstance
import io.github.feelfreelinux.wykopmobilny.models.fragments.removeDataFragment
import io.github.feelfreelinux.wykopmobilny.models.pojo.apiv2.models.TagMetaResponse
import io.github.feelfreelinux.wykopmobilny.models.pojo.apiv2.models.TagStateResponse
import io.github.feelfreelinux.wykopmobilny.ui.modules.NavigatorApi
import io.github.feelfreelinux.wykopmobilny.ui.modules.mikroblog.feed.tag.entries.TagEntriesFragment
import io.github.feelfreelinux.wykopmobilny.utils.printout
import io.github.feelfreelinux.wykopmobilny.utils.usermanager.UserManagerApi
import io.github.feelfreelinux.wykopmobilny.utils.wykop_link_handler.linkparser.TagLinkParser
import kotlinx.android.synthetic.main.activity_tag.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class TagActivity : BaseActivity() {
    private lateinit var entryTag : String
    @Inject lateinit var navigator : NavigatorApi

    companion object {
        val EXTRA_TAG = "EXTRA_TAG"

        fun createIntent(context : Context, tag : String): Intent {
            val intent = Intent(context, TagActivity::class.java)
            intent.putExtra(TagActivity.EXTRA_TAG, tag)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag)
        setSupportActionBar(toolbar)
        WykopApp.uiInjector.inject(this)

        entryTag = intent.getStringExtra(EXTRA_TAG)?: TagLinkParser.getTag(intent.data.toString())

        fab.setOnClickListener {
            navigator.openAddEntryActivity(this)
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "#" + entryTag
        }

        supportFragmentManager.beginTransaction().replace(R.id.contentView,
                TagEntriesFragment.newInstance(entryTag)).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}