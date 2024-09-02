#ifndef CC_ITER_CONTAINS_H
#define CC_ITER_CONTAINS_H

#include<stddef.h>

/**
 *  "Implement an iterator"
 */
typedef int (*cc_iter_next_fn_t)(void *self, void **item, size_t *index);

struct cc_iter_i{
    cc_iter_next_fn_t next;
};
static inline int cc_iter_next(void *self, void *item, size_t *index){
    return  (*(struct cc_iter_i **)self)->next(self, (void **)item, index);
}

#endif //CC_ITER_CONTAINS_H