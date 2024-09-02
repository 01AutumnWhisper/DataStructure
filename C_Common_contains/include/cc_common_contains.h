#ifndef CC_COMMON_CONTAINS_H
#define CC_COMMON_CONTAINS_H

#include<stddef.h>

// enum cc_traverse_direction {
//     CC_TRAVERSE_FORWARD = 4,
//     CC_TRAVERSE_BACKWARD,

// }

/**
 *  You should pass a double pointer -such as &p
 * @param p : void*
 * Resets pointer(p) to NULL
 * @returns 0 if success, 1 if p is NULL
 */
static inline int reset_double_ptr(void *p){
    if(p==NULL){
        return 1;
    }
    else{
        *(void **)p =  NULL;
        return 0;
    }
}

/**
 *  函数指针，单值函数指针， 比较函数指针， 哈希函数指针
 */
typedef int (*cc_simple_fn_1_t)(void *value);
typedef int (*cc_cmp_fn_t)(void *left, void *right);
typedef size_t (*cc_hash_fn_t)(void *obj);

/**
 *  函数模块， 比较， 哈希值计算， 打印退出信息， 打印debug信息。
 */

int cc_default_cmp_fn(void *left, void *right);

// Return obj directly as the value
size_t cc_default_cmp_fn(void *obj);

// address by 4/8 bytes asigned , make a good hash value!
size_t cc_address_hash_fn(void *obj);

// Calculate a hash from String simply
size_t cc_str_hash_fn_simple(void *obj);

//Calculate hash from String in the BKDR (31, 131, 1313, ...)
size_t cc_str_hash_fn_bkdr(void *obj);

int cc_exit_info(int code, char *format, ...);

// 
int cc_debug_print(char *format, ...);

int cc_print_n(char *s, int n);

#endif // CC_COMMON_CONTAINS_H