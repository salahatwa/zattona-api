package run.halo.app.model.enums;

import lombok.Getter;

/**
 * Banned status
 *
 * @author ssatwa
 * @date 2020/1/5
 */
@Getter
public enum BanStatusEnum {
	/**
	 * Banned status
	 */
	NORMAL(0);

	private final int status;

	BanStatusEnum(int status) {
		this.status = status;
	}
}
