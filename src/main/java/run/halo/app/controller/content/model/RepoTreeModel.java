package run.halo.app.controller.content.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RepoTreeModel {
	private List<Tree> tree = new ArrayList<RepoTreeModel.Tree>();

	@Data
	public static class Tree {
		private String path;
		private String mode;
		private String type;
		private String sha;
		private String content;
	}
}
