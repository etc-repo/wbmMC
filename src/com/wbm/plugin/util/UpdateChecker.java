package com.wbm.plugin.util;

import java.io.IOException;

import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class UpdateChecker {
	/**
	 * Get latest github release version
	 * 
	 * @return latest release version
	 */
	public static String getGithubLatestReleaseVersion(int repoId) {
		String version = null;

		try {
			// access github anonymously (not connect())
			GitHub github = GitHub.connectAnonymously();
			GHRepository repo = github.getRepositoryById(repoId);
			GHRelease release = repo.getLatestRelease();
			version = release.getTagName();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return version;
	}
}
