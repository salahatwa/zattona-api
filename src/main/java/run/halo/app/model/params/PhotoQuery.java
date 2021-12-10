package run.halo.app.model.params;

import lombok.Data;

/**
 * Photo query params.
 *
 * @author ssatwa
 * @date 2019/04/25
 */
@Data
public class PhotoQuery {

    /**
     * Keyword.
     */
    private String keyword;

    private String team;
}
