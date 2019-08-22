package abbyy.cloudsdk.v2.client.models.requestparams;

public abstract class RequestParams<T> {
    private Class<T> responseClass;

    protected RequestParams(Class<T> responseClass) {
        this.responseClass = responseClass;
    }

    public Class<T> getResponseClass() {
        return responseClass;
    }
}
