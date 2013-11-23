package jp.jbug.example.seam.test;

import jp.jbug.example.seam.model.BlogEntry;

import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.Test;

public class BlogEntryActionTest extends SeamTest {

	@Test
	public void testConfirm() throws Exception {
		new FacesRequest("/BlogEntry.xhtml") {
			@Override
			protected void updateModelValues() throws Exception {
				setValue("#{blogEntry.blogEntry}", "テストエントリ");
				setValue("#{blogEntry.category}", "テストカテゴリ");
			}

			@Override
			protected void invokeApplication() {
				assert invokeMethod("#{blogEntryAction.confirm()}") == null;
			}

			@Override
			protected void renderResponse() {
				BlogEntry result = (BlogEntry) getInstance("blogEntry");
				assert result.getBlogEntry() == "テストエントリ";
				assert result.getCategory() == "テストカテゴリ";
			}
		}.run();
	}
}
