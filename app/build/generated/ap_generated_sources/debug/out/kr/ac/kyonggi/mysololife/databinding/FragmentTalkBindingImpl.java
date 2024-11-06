package kr.ac.kyonggi.mysololife.databinding;
import kr.ac.kyonggi.mysololife.R;
import kr.ac.kyonggi.mysololife.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentTalkBindingImpl extends FragmentTalkBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.BoardListView, 1);
        sViewsWithIds.put(R.id.writeBtn, 2);
        sViewsWithIds.put(R.id.linearLayout4, 3);
        sViewsWithIds.put(R.id.linearLayout3, 4);
        sViewsWithIds.put(R.id.homeTap, 5);
        sViewsWithIds.put(R.id.tipTap, 6);
        sViewsWithIds.put(R.id.talkTap, 7);
        sViewsWithIds.put(R.id.bookmarkTap, 8);
        sViewsWithIds.put(R.id.storeTap, 9);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentTalkBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private FragmentTalkBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ListView) bindings[1]
            , (android.widget.ImageView) bindings[8]
            , (android.widget.ImageView) bindings[5]
            , (android.widget.LinearLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.ImageView) bindings[6]
            , (android.widget.ImageView) bindings[2]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}