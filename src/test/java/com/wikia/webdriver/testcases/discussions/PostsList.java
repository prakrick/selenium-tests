package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.discussions.PostsListPage;

import org.testng.annotations.Test;

/**
 * @ownership Social Wikia
 */
@Test(groups = "Discussions")
public class PostsList extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1366x768";
  private static final String MOBILE_RESOLUTION = "600x800";

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonSeePostsListLoadedOnMobile() {
    postsListLoads();
  }

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonCanSortPostsListOnMobile() {
    userCanSortPostsList();
  }

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonCanViewMorePosts() {
    userCanViewMorePosts();
  }

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.ANONYMOUS)
  public void mobileClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonSeePostsListLoadedOnDesktop() {
    postsListLoads();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void anonDesktopCanViewMorePosts() {
    userCanViewMorePosts();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void clickBackToWiki() {
    backToWiki();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void desktopClickAvatar() {
    clickAvatarLoadsUserPage();
  }

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.ANONYMOUS)
  public void desktopClickUsername() {
    clickUsernameLoadsUserPage();
  }

  /**
   * LOGGED IN USER ON MOBILE SECTION
   */

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.USER_3)
  public void loggedInUserCanSortPostsListOnMobile() {
    userCanSortPostsList();
  }

  @Test
  @Execute(browserSize = MOBILE_RESOLUTION, asUser = User.USER_3)
  public void loggedInUserSeePostsListLoadedOnMobile() {
    postsListLoads();
  }

  /**
   * LOGGED IN USER ON DESKTOP SECTION
   */

  @Test
  @Execute(browserSize = DESKTOP_RESOLUTION, asUser = User.USER_3)
  public void loggedInUserSeePostsListLoadedOnDesktop() {
    postsListLoads();
  }

  /**
   * TESTING METHODS SECTION
   */

  public void postsListLoads() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertFalse(postsList.isPostListEmpty());
  }

  public void userCanSortPostsList() {
    PostsListPage postsList = new PostsListPage(driver).open();
    Assertion.assertTrue(postsList.clickOnSortButtonMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickOnLatestLinkMobile().getSortButtonLabel(), "Latest");
    Assertion.assertTrue(postsList.clickOnSortButtonMobile().isSortListVisibleMobile());
    Assertion.assertEquals(postsList.clickOnTrendingLinkMobile().getSortButtonLabel(), "Trending");
  }

  public void userCanViewMorePosts() {
    PostsListPage postsList = new PostsListPage(driver).open();
    int startingListLength = postsList.getPostsListLength();
    postsList.scrollToBottom(driver);
    Assertion.assertTrue(startingListLength < postsList.getPostsListLength());
  }

  public void backToWiki() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.clickBackToWikiLink();
    postsList.verifyUrl(wikiURL);
  }

  public void clickAvatarLoadsUserPage() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.verifyAvatarLoadsUserPage();
  }

  public void clickUsernameLoadsUserPage() {
    PostsListPage postsList = new PostsListPage(driver).open();
    postsList.verifyUsernameLoadsUserPage();
  }
}
