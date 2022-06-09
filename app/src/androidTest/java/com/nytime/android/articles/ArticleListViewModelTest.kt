package com.nytime.android.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nytime.android.BuildConfig
import com.nytime.android.network.ApiService
import com.nytime.android.network.Result
import com.nytime.android.network.models.ArticlesResponse
import com.nytime.android.network.repository.ArticleRepository
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ArticleListViewModelTest : TestCase() {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: ArticleListViewModel
    private var apiKey: String = BuildConfig.API_KEY

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var articleDataRepository: ArticleRepository

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        articleDataRepository = ArticleRepository(apiService, apiKey)
        viewModel = ArticleListViewModel(articleDataRepository)
    }

    @Test
    fun getArticlesTest() {

        runBlocking {
            var articleItem =  getArticle()
            var articleResonse = ArticlesResponse()
            articleResonse.articles = listOf(articleItem)
            var articleList = Result.Success<ArticlesResponse>(ArticlesResponse())

            Mockito.`when`(articleDataRepository.getArticles(1))
                .thenReturn(articleList)
            viewModel.fetchArticles(7)
            val result = viewModel.articlesLiveDataLiveData.value
            assertEquals(
                listOf<ArticlesResponse.Article>(
                    getArticle()
                ), result
            )
        }
    }

    private fun getArticle(): ArticlesResponse.Article {
        var article = ArticlesResponse.Article()
        article.id = 123
        article.byline = "Anwar Shaikh"
        article.publishedDate = "2022/02/04"
        return article
    }


}